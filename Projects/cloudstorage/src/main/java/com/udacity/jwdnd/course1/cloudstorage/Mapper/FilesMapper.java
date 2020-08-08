package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {

    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File selectFile(String fileName);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> selectFileList(Integer userId);

    @Insert("INSERT INTO FILES (userid, filename, contenttype, filesize, filedata)" +
            "VALUES(#{userId}, #{fileName}, #{contentType}, #{fileSize}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    void deleteFile(Integer fileId);
}
