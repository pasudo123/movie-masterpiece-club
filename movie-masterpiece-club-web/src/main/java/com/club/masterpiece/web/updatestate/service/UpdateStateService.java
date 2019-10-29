package com.club.masterpiece.web.updatestate.service;

import com.club.masterpiece.web.updatestate.model.UpdateState;
import com.club.masterpiece.web.updatestate.repository.UpdateStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pasudo123 on 2019-10-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
@Transactional
public class UpdateStateService {

    private final UpdateStateRepository updateStateRepository;

    public void doUpdate(String tableName) {

        UpdateState foundUpdateState = updateStateRepository.findById(tableName)
                .orElse(new UpdateState(tableName));

        foundUpdateState.updating();

        updateStateRepository.save(foundUpdateState);
    }
}
