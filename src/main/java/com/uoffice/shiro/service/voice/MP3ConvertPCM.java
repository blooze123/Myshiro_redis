package com.uoffice.shiro.service.voice;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
 

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
/**
 * MP3תPCM Java��ʽʵ��
 * @author С˧ؼ
 * @date 2017��12��6��
 */
public class MP3ConvertPCM {
	/**
	 * MP3ת��PCM�ļ�����
	 * @param mp3filepath ԭʼ�ļ�·��
	 * @param pcmfilepath ת���ļ��ı���·��
	 * @throws Exception 
	 */
	public static void mp3Convertpcm(String mp3filepath,String pcmfilepath) throws Exception{
		File mp3 = new File(mp3filepath);
		File pcm = new File(pcmfilepath);
		//ԭMP3�ļ�תAudioInputStream
		AudioInputStream mp3audioStream = AudioSystem.getAudioInputStream(mp3);
		//��AudioInputStream MP3�ļ� ת��ΪPCM AudioInputStream
		AudioInputStream pcmaudioStream = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED, mp3audioStream);
		//׼��ת�����������OutputStream
		OutputStream os = new FileOutputStream(pcm);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead=pcmaudioStream.read(buffer, 0, 8192))!=-1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		pcmaudioStream.close();
	}
	
//	public static void main(String[] args) {
//        String path = "F://�����ļ�//";
//        String mp3filepath = path + "1542795330668.mp3";
//        String pcmfilepath = path + "a1.pcm";
//
//        try {
//        //   MP3ToPcm.convertMP32PCM(mp3filepath, pcmfilepath);
//    //      MP3ToPcm.playMP3(mp3filepath);
//        	MP3ConvertPCM.mp3Convertpcm(mp3filepath, pcmfilepath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}