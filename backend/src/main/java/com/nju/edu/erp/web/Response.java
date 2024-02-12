package com.nju.edu.erp.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String code;
    private String msg;
    private Object result;

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Response buildSuccess(Object result) {
        return new Response("00000", "Success", result);
    }

    public static Response buildSuccess() {return new Response("00000", "Success", "操作成功");};

    public static Response buildFailed(String code, RuntimeException e) {
        return new Response(code, e.getLocalizedMessage(), null);
    }

    public static Response buildFailed(String code, String msg) {
        return new Response(code, msg, null);
    }

    /**
     * 传递二进制流
     * @param response
     * @param wb SXSSWorkbook 格式的excel表格
     * @param filename 表格的文件名，不需要带后缀
     * @throws UnsupportedEncodingException
     */
    public static void buildBlob(HttpServletResponse response, SXSSFWorkbook wb, String filename)throws UnsupportedEncodingException {
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename+".xlsx", "UTF-8"));
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            wb.write(os);
//            String helloWorld="helloworld";
//            os.write(helloWorld.getBytes(StandardCharsets.UTF_8));
            os.flush();
        } catch ( IOException e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                wb.dispose();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
