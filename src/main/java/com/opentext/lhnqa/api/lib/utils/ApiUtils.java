package com.opentext.lhnqa.api.lib.utils;

import java.io.IOException;

import javax.annotation.Nonnull;

import com.opentext.lhnqa.api.lib.testcases.ReferenceData;
import com.opentext.lhnqa.api.lib.testcases.RestHandler;
import com.opentext.lhnqa.lib.utils.ExtLogger;

public class ApiUtils implements ReferenceData {

	private final static ExtLogger LOGGER = new ExtLogger(ApiUtils.class.toString());
	RestHandler restUtil;
	public String nativesPath;

	public ApiUtils(RestHandler restUtil, String nativesPath) {
		this.restUtil = restUtil;
		this.nativesPath = nativesPath;
	}

	public static enum LegalHoldControlNames {

		LHNOTICECONTROLNAME("hold_notice_attachments[]"),

		LHACKCONTROLNAME("acknowledgement_reminder_notice_attachments[]"),

		LHREMCONTROLNAME("hold_reminder_notice_attachments[]"),

		REEASENCONTROLNAME("release_notice_attachments[]"),

		ESCCONTROLNAME("escalation_notice_attachments[]");


		String mLabel = null;

		/**
		 * Constructor
		 */
		private LegalHoldControlNames

		(final @Nonnull String aLabel) {
			mLabel = aLabel;
		}

		/**
		 * @return unique id of back end to identify actions.
		 */
		public String getLabel() {
			return mLabel;
		}

		public static LegalHoldControlNames getLabelType(String aLabel) {
			switch (aLabel) {
			case "hold_notice_attachments[]":
				return LHNOTICECONTROLNAME;
			case "acknowledgement_reminder_notice_attachments[]":
				return LHACKCONTROLNAME;
			case "hold_reminder_notice_attachments[]":
				return LHREMCONTROLNAME;
			case "release_notice_attachments[]":
				return REEASENCONTROLNAME;
			case "escalation_notice_attachments[]":
				return ESCCONTROLNAME;
			default:
				return LHNOTICECONTROLNAME;
			}
		}
	}

	public static enum LegalHoldNoticeNames {

		HOLD("Hold"),

		ACKNOWLEDGEMENT_REMINDER("Acknowledgement reminder"),

		HOLD_REMINDER("Hold reminder"),

		REEASE("Release"),

		ESCALATION("Escalation");


		String mLabel = null;

		/**
		 * Constructor
		 */
		private LegalHoldNoticeNames

		(final @Nonnull String aLabel) {
			mLabel = aLabel;
		}

		/**
		 * @return unique id of back end to identify actions.
		 */
		public String getLabel() {
			return mLabel;
		}
	}

	public String getLHNoticeName(String controlName) {

		switch (LegalHoldControlNames.getLabelType(controlName)) {
		case LHNOTICECONTROLNAME:
			return LegalHoldNoticeNames.HOLD.getLabel();
		case LHACKCONTROLNAME:
			return LegalHoldNoticeNames.ACKNOWLEDGEMENT_REMINDER.getLabel();
		case LHREMCONTROLNAME:
			return LegalHoldNoticeNames.HOLD_REMINDER.getLabel();
		case REEASENCONTROLNAME:
			return LegalHoldNoticeNames.REEASE.getLabel();
		case ESCCONTROLNAME:
			return LegalHoldNoticeNames.ESCALATION.getLabel();
		default:
			return LegalHoldNoticeNames.HOLD.getLabel();
		}
	}

	public Filebuilder attachDocumentsToLegalHold(String docName,String mimetype,String controlName)
			throws IOException {

		Filebuilder holdNoticeFiles = new Filebuilder();
		if (docName != null) {
			LOGGER.stepLog("Adding " + getLHNoticeName(controlName) + " notice attachment to the request");
			String[] allFiles = docName.isEmpty() ? new String[0]: docName.split(",");
			String[] allFilesMimeTypes = mimetype.isEmpty() ? new String[0]: mimetype.split(",");
			for (int doc = 0; doc < allFiles.length; doc++) {
				holdNoticeFiles.upload(allFiles[doc], nativesPath + allFiles[doc], allFilesMimeTypes[doc],controlName);
			}

		}
		return holdNoticeFiles;
	}

}
