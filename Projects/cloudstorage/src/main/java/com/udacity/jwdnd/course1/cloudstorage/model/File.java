package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {
    private Integer fileId;
    private String fileNmae;
    private String contentType;
    private String fileSize;
    private Integer userId;

    public File(){

    }

    public File(Integer fileId, String fileNmae, String contentType, String fileSize, Integer userId) {
        this.fileId = fileId;
        this.fileNmae = fileNmae;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileNmae() {
        return fileNmae;
    }

    public void setFileNmae(String fileNmae) {
        this.fileNmae = fileNmae;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

/*
CREATE TABLE IF NOT EXISTS FILES (
    fileId INT PRIMARY KEY auto_increment,
    filename VARCHAR,
    contenttype VARCHAR,
    filesize VARCHAR,
    userid INT,
    filedata BLOB,
    foreign key (userid) references USERS(userid)
);

 */