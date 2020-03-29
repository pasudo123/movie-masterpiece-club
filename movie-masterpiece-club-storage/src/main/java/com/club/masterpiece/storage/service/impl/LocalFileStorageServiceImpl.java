package com.club.masterpiece.storage.service.impl;

import com.club.masterpiece.common.attachment.model.Attachment;
import com.club.masterpiece.storage.image.service.ImageUploadService;
import com.club.masterpiece.storage.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Qualifier("LocalFileStorageServiceImpl")
@Service
@RequiredArgsConstructor
public class LocalFileStorageServiceImpl implements FileStorageService {

    private final ImageUploadService imageUploadService;

    @Override
    public List<Attachment> upload(String content) {
        imageUploadService.process(content);
        return null;
    }

    @Override
    public String getContentIfMarking(String content) {
        return imageUploadService.removeImageTagIfPossible(content);
    }
}
