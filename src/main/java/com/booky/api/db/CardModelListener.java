package com.booky.api.db;

import com.booky.api.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class CardModelListener extends AbstractMongoEventListener<Card> {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Card> event) {
        if(event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGeneratorService.generateSequence(Card.SEQUENCE_NAME));
        }
    }
}
