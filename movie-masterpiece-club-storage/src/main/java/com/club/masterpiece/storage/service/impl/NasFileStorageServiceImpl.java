package com.club.masterpiece.storage.service.impl;

import com.club.masterpiece.common.attachment.model.Attachment;
import com.club.masterpiece.storage.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Qualifier("NasFileStorageServiceImpl")
@Service
@RequiredArgsConstructor
public class NasFileStorageServiceImpl implements FileStorageService {

    @Override
    public List<Attachment> upload(String content) {
        return null;
    }

    @Override
    public String getContentIfMarking(String content) {
        return null;
    }
}
