package com.bugs.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckImgServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CheckImgServlet() {
		super();
	}
	private List<String> words = new ArrayList<String>();
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ��ֹ����
		// response.setHeader("Cache-Control", "no-cache");
		// response.setHeader("Pragma", "no-cache");
		// response.setDateHeader("Expires", -1);

		int width = 120;
		int height = 30;

		// ����һ ����һ���ڴ���ͼƬ
		//BufferedImage:����ͼƬ����  ��  BufferedImage.TYPE_INT_RGBͼƬ�������ͣ�rgb����Ԫ��ɫ���
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// ����� ͼƬ���Ʊ�����ɫ ---ͨ����ͼ����
		//Graphics:����   
		Graphics graphics = bufferedImage.getGraphics();// �õ���ͼ���� --- ����
		// �����κ�ͼ��֮ǰ ������ָ��һ����ɫ   //getRandColor()��ȡ���ɫ
		graphics.setColor(getRandColor(200, 250));
		//�����Σ�ʹ��������ɫ�����ο� �����仰���Ǳ���ɫ
		graphics.fillRect(0, 0, width, height);

		// ������ ���Ʊ߿�
		graphics.setColor(Color.WHITE);
		graphics.drawRect(0, 0, width - 1, height - 1);

		// ������ �ĸ��������
		Graphics2D graphics2d = (Graphics2D) graphics;
		// �����������
		graphics2d.setFont(new Font("����", Font.BOLD, 18));

		Random random = new Random();// ���������
		int index = random.nextInt(words.size());
		String word = words.get(index);// ��ó���

		// ����x����
		int x = 10;
		for (int i = 0; i < word.length(); i++) {
			// �����ɫ
			graphics2d.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// ��ת -30 --- 30��
			int jiaodu = random.nextInt(60) - 30;
			// ���㻡��
			double theta = jiaodu * Math.PI / 180;

			// �����ĸ����
			char c = word.charAt(i);

			// ��c �����ͼƬ
			graphics2d.rotate(theta, x, 20);
			graphics2d.drawString(String.valueOf(c), x, 20);
			graphics2d.rotate(-theta, x, 20);
			x += 30;
		}

		// ����֤�����ݱ���session ,session���ݴ洢�ڷ�����
		request.getSession().setAttribute("checkcode_session", word);

		// ������ ���Ƹ�����
		graphics.setColor(getRandColor(160, 200));
		int x1;
		int x2;
		int y1;
		int y2;
		for (int i = 0; i < 30; i++) {
			x1 = random.nextInt(width);
			x2 = random.nextInt(12);
			y1 = random.nextInt(height);
			y2 = random.nextInt(12);
			graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
		}

		// ������ͼƬ���������� ImageIO
		graphics.dispose();// �ͷ���Դ
		
		
		
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		// ��ʼ���׶Σ���ȡnew_words.txt
		// web�����ж�ȡ �ļ�������ʹ�þ��Դ���·��
		String path = getServletContext().getRealPath("/WEB-INF/new_words.txt");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				words.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Color getRandColor(int fc, int bc) {
		// ȡ�������ɫ
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
