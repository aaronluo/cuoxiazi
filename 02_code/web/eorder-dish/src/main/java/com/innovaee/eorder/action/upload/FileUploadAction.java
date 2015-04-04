package com.innovaee.eorder.action.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.action.BaseAction;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.utils.MenuUtil;
import com.innovaee.eorder.utils.MessageUtil;
import com.innovaee.eorder.vo.EOrderUserDetailVO;
import com.innovaee.eorder.vo.MenuLinkVO;

public class FileUploadAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    /** 数据库中对应的功能描述常量 */
    public static final String FUNCTION_DESC = "Upload";

    /** 代表上传文件的file对象 */
    private File file;

    /** 上传文件名 */
    private String fileFileName;

    /** 上传文件的MIME类型 */
    private String fileContentType;

    /** 上传文件的描述信息 */
    private String description;

    /** 保存上传文件的目录，相对于Web应用程序的根路径，在struts.xml文件中配置 */
    private String uploadDir;

    /** 保存的文件名 */
    private String newFileName;

    /** 上传文件的列表 */
    private List<String> dishImages;

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    /**
     * 进入上传页面
     * 
     * @return
     * @throws Exception
     */
    public String upload() {
        return SUCCESS;
    }

    /**
     * 上传图片
     * 
     * @return
     * @throws Exception
     */
    public String uploadImage() {
        // 得到当前时间自1970年1月1日0时0分0秒开始流逝的毫秒数，将这个毫秒数作为上传文件新的文件名。
        long now = new Date().getTime();

        // 得到保存上传文件的目录的真实路径
        String path = ServletActionContext.getServletContext().getRealPath(
                uploadDir);

        File dir = new File(path);
        // 如果这个目录不存在，则创建它。
        if (!dir.exists()) {
            dir.mkdir();
        }

        if (null != fileFileName && !"".equals(fileFileName.trim())) {
            int index = fileFileName.lastIndexOf('.');
            // 判断上传文件名是否有扩展名
            if (index != -1) {
                newFileName = now + fileFileName.substring(index);
            } else {
                newFileName = Long.toString(now);
            }
            this.setNewFileName(newFileName);

            BufferedOutputStream bos = null;
            BufferedInputStream bis = null;
            // 读取保存在临时目录下的上传文件，写入到新的文件中。
            try {
                FileInputStream fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);

                FileOutputStream fos = new FileOutputStream(new File(dir,
                        newFileName));
                bos = new BufferedOutputStream(fos);

                byte[] buf = new byte[4096];

                int len = -1;
                while ((len = bis.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
            } catch (FileNotFoundException e) {
                this.setMessage(MessageUtil.getMessage(
                        "file_not_found_fxception", fileFileName));
                return INPUT;
            } catch (IOException e) {
                this.setMessage(MessageUtil.getMessage("io_exception",
                        newFileName));
                return INPUT;
            } finally {
                try {
                    if (null != bis) {
                        bis.close();
                    }
                    if (null != bos) {
                        bos.close();
                    }
                } catch (IOException e) {
                    this.setMessage(MessageUtil.getMessage("io_exception",
                            newFileName));
                    return INPUT;
                }
            }
        }

        return SUCCESS;
    }

    public List<String> getDishImages() {
        return dishImages;
    }

    public void setDishImages(List<String> dishImages) {
        this.dishImages = dishImages;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

}