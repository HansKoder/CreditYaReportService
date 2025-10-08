package org.pragma.creditya.dynamodb;

import org.pragma.creditya.dynamodb.helper.CustomMapper;
import org.pragma.creditya.model.loanreport.LoanReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoanReportMapper implements CustomMapper<LoanReport, LoanReportEntity> {

    private final Logger logger = LoggerFactory.getLogger(LoanReportMapper.class);

    @Override
    public LoanReportEntity toData(LoanReport entity) {
        logger.info("[infra.adapter.dynamodb.mapper] (toData) payload=[ entity:{} ]", entity);
        LoanReportEntity data = LoanReportEntity.Builder.any()
                .id(entity.getId().getValue())
                .name(entity.getName().value())
                .count(entity.getCount().value())
                .totalAmountApproved(entity.getTotalApprovedAmount().value())
                .build();

        logger.info("[infra.adapter.dynamodb.mapper] (toData) mapped to data, response=[ data:{} ]", data);

        return data;
    }

    @Override
    public LoanReport toEntity(LoanReportEntity data) {
        logger.info("[infra.adapter.dynamodb.mapper] (toEntity) payload=[ data:{} ]", data);

        LoanReport entity = LoanReport.Builder.any()
                .id(data.getId())
                .name(data.getName())
                .count(data.getCount())
                .totalApprovedAmount(data.getTotalAmountApproved())
                .build();

        logger.info("[infra.adapter.dynamodb.mapper] (toEntity) mapped to entity, response=[ entity:{} ]", entity);

        return entity;
    }
}
