package com.bugs.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class ProductID {
	
	public static String getOrderIdByUUId() {
        int machineId = 1;//���֧��1-9����Ⱥ��������
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//�п����Ǹ���
            hashCodeV = - hashCodeV;
        }
//         0 ����ǰ�油��0     
//         4 ������Ϊ4     
//         d �������Ϊ������
        return  machineId+ String.format("%015d", hashCodeV);
    }
	
	public static String getOrderIdByTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;
    }
}
