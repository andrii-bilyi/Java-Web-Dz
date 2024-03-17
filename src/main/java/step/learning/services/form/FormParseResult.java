package step.learning.services.form;

import org.apache.commons.fileupload.FileItem;

import java.util.Map;

public interface FormParseResult {

    Map<String,String> getFields(); //звичайні поля форми
    Map<String, FileItem> getFiles(); //завантажені файли
}
