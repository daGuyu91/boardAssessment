package com.assessment.board.controller;

import javax.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class AbstractController {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(AbstractController.class);

        private static final String FLASH_ERROR_MESSAGE = "errorMessage";
        private static final String FLASH_FEEDBACK_MESSAGE = "feedbackMessage";

        private static final String VIEW_REDIRECT_PREFIX = "redirect:/";

        @Resource
        private MessageSource messageSource;

        protected void addErrorMessage(RedirectAttributes model, String code, Object... params) {
            LOG.debug("adding error message with code: " + code + " and params: " + params);
            Locale current = LocaleContextHolder.getLocale();
            LOG.debug("Current locale is " + current);
            String localizedErrorMessage = messageSource.getMessage(code, params, current);
            LOG.debug("Localized message is: " + localizedErrorMessage);
            model.addFlashAttribute(FLASH_ERROR_MESSAGE, localizedErrorMessage);
        }

        /**
         * Adds a new feedback message.
         * @param model A model which stores the feedback message.
         * @param code  A message code which is used to fetch the actual message from the message source.
         * @param params    The parameters which are attached to the actual feedback message.
         */
        protected void addFeedbackMessage(RedirectAttributes model, String code, Object... params) {
            LOGGER.debug("Adding feedback message with code: " + code + " and params: " + params);
            Locale current = LocaleContextHolder.getLocale();
            LOGGER.debug("Current locale is " + current);
            String localizedFeedbackMessage = messageSource.getMessage(code, params, current);
            LOGGER.debug("Localized message is: " + localizedFeedbackMessage);
            model.addFlashAttribute(FLASH_FEEDBACK_MESSAGE, localizedFeedbackMessage);
        }

        /**
         * Creates a redirect view path for a specific controller action
         * @param path  The path processed by the controller method.
         * @return  A redirect view path to the given controller method.
         */
        protected String createRedirectViewPath(String path) {
            StringBuilder builder = new StringBuilder();
            builder.append(VIEW_REDIRECT_PREFIX);
            builder.append(path);
            return builder.toString();
        }

        /**
         * This method should only be used by unit tests.
         * @param messageSource
         */
        protected void setMessageSource(MessageSource messageSource) {
            this.messageSource = messageSource;
        }
}
