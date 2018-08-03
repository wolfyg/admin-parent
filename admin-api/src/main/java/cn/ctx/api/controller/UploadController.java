/**
 * 
 */
package cn.ctx.api.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ctx.api.base.BaseConfig;
import cn.ctx.api.util.HttpUtil;
import cn.ctx.api.util.Utils;

/**
 * 
 * 文件上传工具
 * @author yg
 *
 */
@RequestMapping("/wx")
@Controller
public class UploadController {

	@Autowired
	private BaseConfig config;

    @RequestMapping(value = "/uploadPic", produces = "aplication/json;charset=utf-8")
    @ResponseBody
	public String picUrl(String base64, String fileName,Model model) {
    	base64=base64.substring(base64.indexOf(",")+1);
		byte[] bytes = Utils.transformBase64(base64);
		InputStream in = new ByteArrayInputStream(bytes);
		return HttpUtil.uploadFile(config.getFile_server_url(), in, fileName);
	}
}
