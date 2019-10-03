//package com.eversis.wrapper;
//
//import com.liferay.journal.model.JournalArticle;
//import com.liferay.journal.service.JournalArticleLocalServiceWrapper;
//
//import com.liferay.portal.kernel.exception.PortalException;
//import com.liferay.portal.kernel.service.ServiceContext;
//import com.liferay.portal.kernel.service.ServiceWrapper;
//
//import org.osgi.service.component.annotations.Component;
//
//import java.io.File;
//import java.util.Locale;
//import java.util.Map;
///**
// * @author patry
// */
//@Component(
//		immediate = true,
//		property = {
//		},
//		service = ServiceWrapper.class
//)
//public class JournalArticleLocalServiceOverride extends JournalArticleLocalServiceWrapper {
//
//	public JournalArticleLocalServiceOverride() {
//		super(null);
//	}
//
//	@Override
//	public JournalArticle updateArticle(long userId, long groupId, long folderId, String articleId, double version,
//			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, Map<Locale, String> friendlyURLMap,
//			String content, String ddmStructureKey, String ddmTemplateKey, String layoutUuid, int displayDateMonth,
//			int displayDateDay, int displayDateYear, int displayDateHour, int displayDateMinute,
//			int expirationDateMonth, int expirationDateDay, int expirationDateYear, int expirationDateHour,
//			int expirationDateMinute, boolean neverExpire, int reviewDateMonth, int reviewDateDay, int reviewDateYear,
//			int reviewDateHour, int reviewDateMinute, boolean neverReview, boolean indexable, boolean smallImage,
//			String smallImageURL, File smallImageFile, Map<String, byte[]> images, String articleURL,
//			ServiceContext serviceContext) throws PortalException {
//
//		System.out.println("1");
//
//		return super
//				.updateArticle(userId, groupId, folderId, articleId, version, titleMap, descriptionMap, friendlyURLMap,
//						content, ddmStructureKey, ddmTemplateKey, layoutUuid, displayDateMonth, displayDateDay,
//						displayDateYear, displayDateHour, displayDateMinute, expirationDateMonth, expirationDateDay,
//						expirationDateYear, expirationDateHour, expirationDateMinute, neverExpire, reviewDateMonth,
//						reviewDateDay, reviewDateYear, reviewDateHour, reviewDateMinute, neverReview, indexable,
//						smallImage, smallImageURL, smallImageFile, images, articleURL, serviceContext);
//	}
//
//	@Override
//	public JournalArticle updateArticle(long userId, long groupId, long folderId, String articleId, double version,
//			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, String content, String layoutUuid,
//			ServiceContext serviceContext) throws PortalException {
//
//		System.out.println("2");
//
//
//		return super.updateArticle(userId, groupId, folderId, articleId, version, titleMap, descriptionMap, content,
//				layoutUuid, serviceContext);
//	}
//
//	@Override
//	public JournalArticle updateArticle(long userId, long groupId, long folderId, String articleId, double version,
//			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, String content, String ddmStructureKey,
//			String ddmTemplateKey, String layoutUuid, int displayDateMonth, int displayDateDay, int displayDateYear,
//			int displayDateHour, int displayDateMinute, int expirationDateMonth, int expirationDateDay,
//			int expirationDateYear, int expirationDateHour, int expirationDateMinute, boolean neverExpire,
//			int reviewDateMonth, int reviewDateDay, int reviewDateYear, int reviewDateHour, int reviewDateMinute,
//			boolean neverReview, boolean indexable, boolean smallImage, String smallImageURL, File smallImageFile,
//			Map<String, byte[]> images, String articleURL, ServiceContext serviceContext) throws PortalException {
//
//		System.out.println("3");
//
//
//		return super.updateArticle(userId, groupId, folderId, articleId, version, titleMap, descriptionMap, content,
//				ddmStructureKey, ddmTemplateKey, layoutUuid, displayDateMonth, displayDateDay, displayDateYear,
//				displayDateHour, displayDateMinute, expirationDateMonth, expirationDateDay, expirationDateYear,
//				expirationDateHour, expirationDateMinute, neverExpire, reviewDateMonth, reviewDateDay, reviewDateYear,
//				reviewDateHour, reviewDateMinute, neverReview, indexable, smallImage, smallImageURL, smallImageFile,
//				images, articleURL, serviceContext);
//	}
//
//	@Override
//	public JournalArticle updateArticle(long userId, long groupId, long folderId, String articleId, double version,
//			String content, ServiceContext serviceContext) throws PortalException {
//
//		System.out.println("4");
//
//
//		return super.updateArticle(userId, groupId, folderId, articleId, version, content, serviceContext);
//	}
//
//	@Override
//	public JournalArticle updateArticle(long id, String urlTitle) throws PortalException {
//		System.out.println("5");
//
//
//
//		return super.updateArticle(id, urlTitle);
//	}
//
//	@Override
//	public JournalArticle updateJournalArticle(JournalArticle journalArticle) {
//
//		System.out.println("6");
//
//
//		return super.updateJournalArticle(journalArticle);
//	}
//
//	//TODO: updateArticleTranslation
//}