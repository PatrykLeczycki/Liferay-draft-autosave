//package com.eversis.journal.portlet.mvc.portlet;
//
//import com.eversis.journal.portlet.mvc.constants.JournalPortletMvcPortletKeys;
//
//import com.liferay.asset.kernel.model.AssetEntry;
//import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
//import com.liferay.journal.exception.ArticleContentSizeException;
//import com.liferay.journal.model.JournalArticle;
//import com.liferay.journal.model.JournalArticleConstants;
//import com.liferay.journal.service.JournalArticleService;
//import com.liferay.journal.web.internal.portlet.JournalPortlet;
//import com.liferay.journal.web.internal.util.JournalUtil;
//import com.liferay.portal.kernel.exception.PortalException;
//import com.liferay.portal.kernel.language.LanguageUtil;
//import com.liferay.portal.kernel.log.Log;
//import com.liferay.portal.kernel.log.LogFactoryUtil;
//import com.liferay.portal.kernel.model.Layout;
//import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
//import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
//
//import javax.portlet.ActionRequest;
//import javax.portlet.ActionResponse;
//import javax.portlet.Portlet;
//import javax.portlet.PortletPreferences;
//
//import com.liferay.portal.kernel.service.ServiceContext;
//import com.liferay.portal.kernel.service.ServiceContextFactory;
//import com.liferay.portal.kernel.servlet.MultiSessionMessages;
//import com.liferay.portal.kernel.theme.ThemeDisplay;
//import com.liferay.portal.kernel.upload.LiferayFileItemException;
//import com.liferay.portal.kernel.upload.UploadException;
//import com.liferay.portal.kernel.upload.UploadPortletRequest;
//import com.liferay.portal.kernel.util.HtmlUtil;
//import com.liferay.portal.kernel.util.LocaleUtil;
//import com.liferay.portal.kernel.util.LocalizationUtil;
//import com.liferay.portal.kernel.util.MapUtil;
//import com.liferay.portal.kernel.util.ParamUtil;
//import com.liferay.portal.kernel.util.Portal;
//import com.liferay.portal.kernel.util.StringPool;
//import com.liferay.portal.kernel.util.Validator;
//import com.liferay.portal.kernel.util.WebKeys;
//import com.liferay.portal.kernel.workflow.WorkflowConstants;
//import org.graalvm.compiler.core.common.Fields;
//import com.liferay.portal.kernel.util.PropsKeys;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.Reference;
//
//import java.io.File;
//import java.util.Calendar;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Objects;
//
//@Component(
//	immediate = true,
//	property = {
//		"com.liferay.portlet.display-category=category.sample",
//		"com.liferay.portlet.header-portlet-css=/css/main.css",
//		"com.liferay.portlet.instanceable=true",
//		"javax.portlet.display-name=JournalPortletMvc",
//		"javax.portlet.init-param.template-path=/",
//		"javax.portlet.init-param.view-template=/view.jsp",
//		"javax.portlet.name=" + JournalPortletMvcPortletKeys.JOURNALPORTLETMVC,
//		"javax.portlet.resource-bundle=content.Language",
//		"javax.portlet.security-role-ref=power-user,user"
//	},
//	service = Portlet.class
//)
//public class JournalPortletMvcPortlet extends MVCPortlet {
//
//	public void updateArticle(
//			ActionRequest actionRequest, ActionResponse actionResponse)
//			throws Exception {
//
//		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
//				WebKeys.THEME_DISPLAY);
//
//		UploadException uploadException =
//				(UploadException)actionRequest.getAttribute(
//						WebKeys.UPLOAD_EXCEPTION);
//
//		if (uploadException != null) {
//			Throwable cause = uploadException.getCause();
//
//			if (uploadException.isExceededLiferayFileItemSizeLimit()) {
//				throw new LiferayFileItemException(cause);
//			}
//
//			if (uploadException.isExceededFileSizeLimit() ||
//					uploadException.isExceededUploadRequestSizeLimit()) {
//
//				throw new ArticleContentSizeException(cause);
//			}
//
//			throw new PortalException(cause);
//		}
//
//		UploadPortletRequest uploadPortletRequest =
//				_portal.getUploadPortletRequest(actionRequest);
//
//		if (_log.isDebugEnabled()) {
//			_log.debug(
//					"Updating article " +
//							MapUtil.toString(uploadPortletRequest.getParameterMap()));
//		}
//
//		String actionName = ParamUtil.getString(
//				actionRequest, ActionRequest.ACTION_NAME);
//
//		long groupId = ParamUtil.getLong(uploadPortletRequest, "groupId");
//		long folderId = ParamUtil.getLong(uploadPortletRequest, "folderId");
//		long classNameId = ParamUtil.getLong(
//				uploadPortletRequest, "classNameId");
//		long classPK = ParamUtil.getLong(uploadPortletRequest, "classPK");
//		String articleId = ParamUtil.getString(
//				uploadPortletRequest, "articleId");
//		boolean autoArticleId = ParamUtil.getBoolean(
//				uploadPortletRequest, "autoArticleId");
//		double version = ParamUtil.getDouble(uploadPortletRequest, "version");
//		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
//				actionRequest, "titleMapAsXML");
//
//		String ddmStructureKey = ParamUtil.getString(
//				uploadPortletRequest, "ddmStructureKey");
//
//		DDMStructure ddmStructure = _ddmStructureLocalService.getStructure(
//				_portal.getSiteGroupId(groupId),
//				_portal.getClassNameId(JournalArticle.class), ddmStructureKey,
//				true);
//
//		ServiceContext serviceContext = ServiceContextFactory.getInstance(
//				JournalArticle.class.getName(), uploadPortletRequest);
//
//		Fields fields = DDMUtil.getFields(
//				ddmStructure.getStructureId(), serviceContext);
//
//		String content = _journalConverter.getContent(ddmStructure, fields);
//
//		Locale articleDefaultLocale = LocaleUtil.fromLanguageId(
//				LocalizationUtil.getDefaultLanguageId(content));
//
//		if ((classNameId == JournalArticleConstants.CLASSNAME_ID_DEFAULT) &&
//				!_hasDefaultLocale(titleMap, articleDefaultLocale)) {
//
//			titleMap.put(
//					articleDefaultLocale,
//					LanguageUtil.format(
//							_portal.getHttpServletRequest(actionRequest), "untitled-x",
//							HtmlUtil.escape(
//									ddmStructure.getName(themeDisplay.getLocale()))));
//		}
//
//		Map<Locale, String> descriptionMap =
//				LocalizationUtil.getLocalizationMap(
//						actionRequest, "descriptionMapAsXML");
//		Map<Locale, String> friendlyURLMap =
//				LocalizationUtil.getLocalizationMap(actionRequest, "friendlyURL");
//
//		String ddmTemplateKey = ParamUtil.getString(
//				uploadPortletRequest, "ddmTemplateKey");
//		int displayPageType = ParamUtil.getInteger(
//				uploadPortletRequest, "displayPageType");
//
//		String layoutUuid = ParamUtil.getString(
//				uploadPortletRequest, "layoutUuid");
//
//		if ((displayPageType == AssetDisplayPageConstants.TYPE_DEFAULT) ||
//				(displayPageType == AssetDisplayPageConstants.TYPE_SPECIFIC)) {
//
//			Layout targetLayout = _journalHelper.getArticleLayout(
//					layoutUuid, groupId);
//
//			JournalArticle latestArticle = _journalArticleService.fetchArticle(
//					groupId, articleId);
//
//			if ((displayPageType == AssetDisplayPageConstants.TYPE_SPECIFIC) &&
//					(targetLayout == null) && (latestArticle != null) &&
//					Validator.isNotNull(latestArticle.getLayoutUuid())) {
//
//				Layout latestTargetLayout = _journalHelper.getArticleLayout(
//						latestArticle.getLayoutUuid(), groupId);
//
//				if (latestTargetLayout == null) {
//					layoutUuid = latestArticle.getLayoutUuid();
//				}
//			}
//			else if (targetLayout == null) {
//				layoutUuid = null;
//			}
//		}
//		else {
//			layoutUuid = null;
//		}
//
//		int displayDateMonth = ParamUtil.getInteger(
//				uploadPortletRequest, "displayDateMonth");
//		int displayDateDay = ParamUtil.getInteger(
//				uploadPortletRequest, "displayDateDay");
//		int displayDateYear = ParamUtil.getInteger(
//				uploadPortletRequest, "displayDateYear");
//		int displayDateHour = ParamUtil.getInteger(
//				uploadPortletRequest, "displayDateHour");
//		int displayDateMinute = ParamUtil.getInteger(
//				uploadPortletRequest, "displayDateMinute");
//		int displayDateAmPm = ParamUtil.getInteger(
//				uploadPortletRequest, "displayDateAmPm");
//
//		if (displayDateAmPm == Calendar.PM) {
//			displayDateHour += 12;
//		}
//
//		int expirationDateMonth = ParamUtil.getInteger(
//				uploadPortletRequest, "expirationDateMonth");
//		int expirationDateDay = ParamUtil.getInteger(
//				uploadPortletRequest, "expirationDateDay");
//		int expirationDateYear = ParamUtil.getInteger(
//				uploadPortletRequest, "expirationDateYear");
//		int expirationDateHour = ParamUtil.getInteger(
//				uploadPortletRequest, "expirationDateHour");
//		int expirationDateMinute = ParamUtil.getInteger(
//				uploadPortletRequest, "expirationDateMinute");
//		int expirationDateAmPm = ParamUtil.getInteger(
//				uploadPortletRequest, "expirationDateAmPm");
//
//		boolean neverExpire = ParamUtil.getBoolean(
//				uploadPortletRequest, "neverExpire", displayDateYear == 0);
//
//		if (!PropsValues.SCHEDULER_ENABLED) {
//			neverExpire = true;
//		}
//
//		if (expirationDateAmPm == Calendar.PM) {
//			expirationDateHour += 12;
//		}
//
//		int reviewDateMonth = ParamUtil.getInteger(
//				uploadPortletRequest, "reviewDateMonth");
//		int reviewDateDay = ParamUtil.getInteger(
//				uploadPortletRequest, "reviewDateDay");
//		int reviewDateYear = ParamUtil.getInteger(
//				uploadPortletRequest, "reviewDateYear");
//		int reviewDateHour = ParamUtil.getInteger(
//				uploadPortletRequest, "reviewDateHour");
//		int reviewDateMinute = ParamUtil.getInteger(
//				uploadPortletRequest, "reviewDateMinute");
//		int reviewDateAmPm = ParamUtil.getInteger(
//				uploadPortletRequest, "reviewDateAmPm");
//
//		boolean neverReview = ParamUtil.getBoolean(
//				uploadPortletRequest, "neverReview", displayDateYear == 0);
//
//		if (!PropsValues.SCHEDULER_ENABLED) {
//			neverReview = true;
//		}
//
//		if (reviewDateAmPm == Calendar.PM) {
//			reviewDateHour += 12;
//		}
//
//		boolean indexable = ParamUtil.getBoolean(
//				uploadPortletRequest, "indexable");
//
//		String smallImageSource = ParamUtil.getString(
//				uploadPortletRequest, "smallImageSource", "none");
//
//		boolean smallImage = !Objects.equals(smallImageSource, "none");
//
//		String smallImageURL = StringPool.BLANK;
//		File smallFile = null;
//
//		if (Objects.equals(smallImageSource, "url")) {
//			smallImageURL = ParamUtil.getString(
//					uploadPortletRequest, "smallImageURL");
//		}
//		else if (Objects.equals(smallImageSource, "file")) {
//			smallFile = uploadPortletRequest.getFile("smallFile");
//		}
//
//		String articleURL = ParamUtil.getString(
//				uploadPortletRequest, "articleURL");
//
//		JournalArticle article = null;
//		String oldUrlTitle = StringPool.BLANK;
//
//		if (actionName.equals("addArticle")) {
//
//			// Add article
//
//			article = _journalArticleService.addArticle(
//					groupId, folderId, classNameId, classPK, articleId,
//					autoArticleId, titleMap, descriptionMap, friendlyURLMap,
//					content, ddmStructureKey, ddmTemplateKey, layoutUuid,
//					displayDateMonth, displayDateDay, displayDateYear,
//					displayDateHour, displayDateMinute, expirationDateMonth,
//					expirationDateDay, expirationDateYear, expirationDateHour,
//					expirationDateMinute, neverExpire, reviewDateMonth,
//					reviewDateDay, reviewDateYear, reviewDateHour, reviewDateMinute,
//					neverReview, indexable, smallImage, smallImageURL, smallFile,
//					null, articleURL, serviceContext);
//		}
//		else {
//
//			// Update article
//
//			article = _journalArticleService.getArticle(
//					groupId, articleId, version);
//
//			String tempOldUrlTitle = article.getUrlTitle();
//
//			if (actionName.equals("previewArticle") ||
//					actionName.equals("updateArticle")) {
//
//				article = _journalArticleService.updateArticle(
//						groupId, folderId, articleId, version, titleMap,
//						descriptionMap, friendlyURLMap, content, ddmStructureKey,
//						ddmTemplateKey, layoutUuid, displayDateMonth,
//						displayDateDay, displayDateYear, displayDateHour,
//						displayDateMinute, expirationDateMonth, expirationDateDay,
//						expirationDateYear, expirationDateHour,
//						expirationDateMinute, neverExpire, reviewDateMonth,
//						reviewDateDay, reviewDateYear, reviewDateHour,
//						reviewDateMinute, neverReview, indexable, smallImage,
//						smallImageURL, smallFile, null, articleURL, serviceContext);
//			}
//
//			if (!tempOldUrlTitle.equals(article.getUrlTitle())) {
//				oldUrlTitle = tempOldUrlTitle;
//			}
//		}
//
//		// Recent articles
//
//		JournalUtil.addRecentArticle(actionRequest, article);
//
//		// Journal content
//
//		String portletResource = ParamUtil.getString(
//				actionRequest, "portletResource");
//
//		long refererPlid = ParamUtil.getLong(actionRequest, "refererPlid");
//
//		if (Validator.isNotNull(portletResource) && (refererPlid > 0)) {
//			AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
//					JournalArticle.class.getName(), article.getResourcePrimKey());
//
//			Layout layout = _layoutLocalService.getLayout(refererPlid);
//
//			PortletPreferences portletPreferences =
//					PortletPreferencesFactoryUtil.getStrictPortletSetup(
//							layout, portletResource);
//
//			if (portletPreferences != null) {
//				portletPreferences.setValue(
//						"groupId", String.valueOf(article.getGroupId()));
//				portletPreferences.setValue(
//						"articleId", article.getArticleId());
//
//				if (assetEntry != null) {
//					portletPreferences.setValue(
//							"assetEntryId",
//							String.valueOf(assetEntry.getEntryId()));
//				}
//
//				portletPreferences.store();
//
//				updateContentSearch(
//						refererPlid, portletResource, article.getArticleId());
//			}
//
//			if (assetEntry != null) {
//				_updateAssetEntryUsage(
//						groupId, assetEntry, portletResource, refererPlid,
//						serviceContext);
//			}
//		}
//
//		// Asset display page
//
//		_assetDisplayPageEntryFormProcessor.process(
//				JournalArticle.class.getName(), article.getResourcePrimKey(),
//				actionRequest);
//
//		int workflowAction = ParamUtil.getInteger(
//				actionRequest, "workflowAction", WorkflowConstants.ACTION_PUBLISH);
//
//		if (Validator.isNotNull(portletResource) &&
//				(workflowAction != WorkflowConstants.ACTION_SAVE_DRAFT)) {
//
//			MultiSessionMessages.add(
//					actionRequest, portletResource + "requestProcessed");
//		}
//
//		sendEditArticleRedirect(
//				actionRequest, actionResponse, article, oldUrlTitle);
//
//		boolean hideDefaultSuccessMessage = ParamUtil.getBoolean(
//				actionRequest, "hideDefaultSuccessMessage");
//
//		if (hideDefaultSuccessMessage) {
//			hideDefaultSuccessMessage(actionRequest);
//		}
//	}
//
//	private static final Log _log = LogFactoryUtil.getLog(JournalPortlet.class);
//
//	@Reference
//	private JournalArticleService _journalArticleService;
//
//	@Reference
//	private Portal _portal;
//
//}
//z