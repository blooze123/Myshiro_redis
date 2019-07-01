package com.uoffice.shiro.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import com.uoffice.shiro.service.voice.MP3ConvertPCM;
import com.uoffice.shiro.service.voice.MyRecord;
import com.uoffice.shiro.service.voice.Sample;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("voice")
public class VoiceController {
	    
	@Resource(name="myRecord")
	private MyRecord mr;

	@RequestMapping("interVoicePage")
	public String interVoicePage(){
		return "voice/voice";
	}

	@RequestMapping("voiceBegin")
	public String voiceBegin(String flag) {
		mr.capture();
		return "voice/voice";
	}

	@ResponseBody
	@RequestMapping(value="voiceEnd",produces="text/html;charset=UTF-8")
	public String voiceEnd(String flag,HttpServletRequest request) {
		String word=null;
		//关闭录音
		mr.setStopflag(true);
		//获得项目下的路径（到webapp的位置）
		String basepath=request.getSession().getServletContext().getRealPath("");
		//保存mp3文件
		String mp3path=mr.save(basepath);
		String pcmpath=basepath+"static/pcm/a1.pcm";
		try {
			System.out.println(mp3path);
			//将mp3文件转换成pcm文件，并且保存在项目中
			MP3ConvertPCM.mp3Convertpcm(mp3path,pcmpath);
			word= Sample.beginSample(pcmpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return word;
	}
}
