package com.eversis.wrapper;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceWrapper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
/**
 * @author patry
 */
@Component(
	immediate = true,
	property = {
	},
	service = ServiceWrapper.class
)
public class JournalArticleLocalServiceOverride extends JournalArticleLocalServiceWrapper {

    Logger logger = LoggerFactory.getLogger(JournalArticleLocalServiceOverride.class);

	public JournalArticleLocalServiceOverride() {
		super(null);
	}

	@Override
	public JournalArticle updateArticle(long userId, long groupId, long folderId, String articleId, double version,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, Map<Locale, String> friendlyURLMap,
			String content, String ddmStructureKey, String ddmTemplateKey, String layoutUuid, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay, int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire, int reviewDateMonth, int reviewDateDay, int reviewDateYear,
			int reviewDateHour, int reviewDateMinute, boolean neverReview, boolean indexable, boolean smallImage,
			String smallImageURL, File smallImageFile, Map<String, byte[]> images, String articleURL,
			ServiceContext serviceContext) throws PortalException {

		logger.info("1");
		//logger.info("old content = " + content);

		serviceContext.setFormDate(new Date());

		JournalArticle test = super
				.updateArticle(userId, groupId, folderId, articleId, version, titleMap, descriptionMap, friendlyURLMap,
						content, ddmStructureKey, ddmTemplateKey, layoutUuid, displayDateMonth, displayDateDay,
						displayDateYear, displayDateHour, displayDateMinute, expirationDateMonth, expirationDateDay,
						expirationDateYear, expirationDateHour, expirationDateMinute, neverExpire, reviewDateMonth,
						reviewDateDay, reviewDateYear, reviewDateHour, reviewDateMinute, neverReview, indexable,
						smallImage, smallImageURL, smallImageFile, images, articleURL, serviceContext);

		logger.info(test.toString());

		//logger.info("new content = " + content);
		return test;
	}

	@Override
	public JournalArticle updateArticle(long userId, long groupId, long folderId, String articleId, double version,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, String content, String layoutUuid,
			ServiceContext serviceContext) throws PortalException {

		logger.info("2");


		return super.updateArticle(userId, groupId, folderId, articleId, version, titleMap, descriptionMap, content,
				layoutUuid, serviceContext);
	}



	@Override
	public JournalArticle updateArticle(long userId, long groupId, long folderId, String articleId, double version,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, String content, String ddmStructureKey,
			String ddmTemplateKey, String layoutUuid, int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour, int expirationDateMinute, boolean neverExpire,
			int reviewDateMonth, int reviewDateDay, int reviewDateYear, int reviewDateHour, int reviewDateMinute,
			boolean neverReview, boolean indexable, boolean smallImage, String smallImageURL, File smallImageFile,
			Map<String, byte[]> images, String articleURL, ServiceContext serviceContext) throws PortalException {

		logger.info("3");


		return super.updateArticle(userId, groupId, folderId, articleId, version, titleMap, descriptionMap, content,
				ddmStructureKey, ddmTemplateKey, layoutUuid, displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, neverExpire, reviewDateMonth, reviewDateDay, reviewDateYear,
				reviewDateHour, reviewDateMinute, neverReview, indexable, smallImage, smallImageURL, smallImageFile,
				images, articleURL, serviceContext);
	}

	@Override
	public JournalArticle updateArticle(long userId, long groupId, long folderId, String articleId, double version,
			String content, ServiceContext serviceContext) throws PortalException {

		logger.info("4");


		return super.updateArticle(userId, groupId, folderId, articleId, version, content, serviceContext);
	}

	@Override
	public JournalArticle updateArticle(long id, String urlTitle) throws PortalException {
		logger.info("5");



		return super.updateArticle(id, urlTitle);
	}

	@Override
	public JournalArticle updateJournalArticle(JournalArticle journalArticle) {

		logger.info("6");
		return super.updateJournalArticle(journalArticle);
	}

	@Override
	public JournalArticle updateArticleTranslation(long groupId, String articleId, double version, Locale locale,
			String title, String description, String content, Map<String, byte[]> images, ServiceContext serviceContext)
			throws PortalException {

		logger.info("7");

		logger.info("@@@\n" + content);

//        logger.info(String.valueOf(serviceContext.getFormDate()));
//
//
//        logger.info("groupId = " + groupId + "\r\narticleId = " + articleId + "\r\nversion = " + version + "\r\nlocale = " + locale + "\r\ntitle = " +
//				title + "\r\ndescription = " + description + "\r\ncontent = " + content + "\r\nimages = " + images + "\r\nserviceContext = " + serviceContext);

		JournalArticle ja = super.updateArticleTranslation(groupId, articleId, version, locale, title, description, content, images,
				serviceContext);

		//logger.info(ja.toString());

        logger.info("new content = " + content);

		return ja;
	}
}