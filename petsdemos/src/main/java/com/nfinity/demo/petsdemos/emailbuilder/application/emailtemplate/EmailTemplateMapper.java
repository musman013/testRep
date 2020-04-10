package com.nfinity.demo.petsdemos.emailbuilder.application.emailtemplate;

import org.mapstruct.Mapper;

import com.nfinity.demo.petsdemos.emailbuilder.application.emailtemplate.dto.*;
import com.nfinity.demo.petsdemos.emailbuilder.domain.model.EmailTemplateEntity;

@Mapper(componentModel = "spring")
public interface EmailTemplateMapper {

    EmailTemplateEntity createEmailTemplateInputToEmailTemplateEntity(CreateEmailTemplateInput emailDto);

    CreateEmailTemplateOutput emailTemplateEntityToCreateEmailTemplateOutput(EmailTemplateEntity entity);

    EmailTemplateEntity updateEmailTemplateInputToEmailTemplateEntity(UpdateEmailTemplateInput emailDto);

    UpdateEmailTemplateOutput emailTemplateEntityToUpdateEmailTemplateOutput(EmailTemplateEntity entity);

    FindEmailTemplateByIdOutput emailTemplateEntityToFindEmailTemplateByIdOutput(EmailTemplateEntity entity);

    FindEmailTemplateByNameOutput emailTemplateEntityToFindEmailTemplateByNameOutput(EmailTemplateEntity entity);
}