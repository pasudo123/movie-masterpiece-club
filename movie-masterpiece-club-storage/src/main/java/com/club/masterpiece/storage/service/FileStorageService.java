package com.club.masterpiece.storage.service;

import com.club.masterpiece.common.attachment.model.Attachment;

import java.util.List;

public interface FileStorageService {

    List<Attachment> upload(String content);

    String getContentIfMarking(String content);

}
