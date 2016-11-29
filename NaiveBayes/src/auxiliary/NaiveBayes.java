/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xhui.recmd.core.bayes.auxiliary;

import java.util.ArrayList;

/**
 *
 * @author Michael Kong
 */
public class NaiveBayes extends Classifier {

	boolean isClassfication[];
   ArrayList <Double>lblClass=new ArrayList<Double>();  //�洢Ŀ��ֵ������
   ArrayList<Integer>lblCount=new ArrayList<Integer>();//�洢Ŀ��ֵ�ĸ���
   ArrayList<Float>lblProba=new ArrayList<Float>();//�洢��Ӧ��label�ĸ���
   CountProbility countlblPro;
   /*@ClassListBasedLabel�ǽ�ѵ�����鰴�� label��˳��������洢*/
   ArrayList<ArrayList<ArrayList<Double>>> ClassListBasedLabel=new  ArrayList<ArrayList<ArrayList<Double>>> ();
    public NaiveBayes() {
    }
    @Override
    /**
     * @train��Ҫ�����һЩ����
     * 1.labels�еĲ�ͬȡֵ�ĸ���f(Yi);  ��Ӧ28,29�����δ���
     * 2.��ѵ�����鰴Ŀ��ֵ����洢   ��37�д���

     * */
    public void train(boolean[] isCategory, double[][] features, double[] labels){
    	isClassfication=isCategory;
    	countlblPro=new CountProbility(isCategory,features,labels);
    	countlblPro.getlblClass(lblClass, lblCount, lblProba);  	
    	ArrayList<ArrayList<Double>> trainingList=countlblPro.UnionFeaLbl(features, labels); //union the features[][] and labels[]
    	ClassListBasedLabel=countlblPro.getClassListBasedLabel(lblClass, trainingList);
    }
    @Override
    /**3.��Y�������£�����Xi�ĸ��� f(Xi/Y)��
     * 4.����ʹ��Yi*Xi*...���������Ǹ�label��ȡֵ
     * */
    public double predict(double[] features) {
    	
    	int max_index; //���ڼ�¼ʹ����ȡ�������Ǹ�����
    	int index=0; //��������� ��ʶ��ͬ��labels ����Ӧ�ĸ���
    	ArrayList<Double> pro_=new ArrayList<Double>(); //������������Ǵ洢features[] �ڲ�ͬlabels�¶�Ӧ�ĸ���
		for(ArrayList<ArrayList<Double>> elements: ClassListBasedLabel)  //����ȡ��ͬ��labelֵ��Ӧ��Ԫ�漯��
		{
		 	ArrayList<Double> pro=new ArrayList<Double>();//��ͬһ��label��Ӧ�����и��ʣ�֮�����е�Ԫ���Գ�
			double probility=1.0; //������ʵĳ˻�
			
			for(int i=0;i<features.length;i++)
			{
				if(isClassfication[i]) //���ڶ����Ե���ɢ�����������ж�
				{
					
					int count=0;
					for(ArrayList<Double> element:elements) //����ȡlabels�е�����Ԫ��
					{
						if(element.get(i).equals(features[i])) //������Ԫ��ĵ�index���ݺ�b��ȣ���ô��count�ͼ�1
							count++;
					}
					if(count==0)
					{
						pro.add(1/(double)(elements.size()+1));
					}
					else
						pro.add(count/(double)elements.size()); //ͳ��������֮��  �������ֵ ������
				}
				else
				{

		  			double Sdev;
		    		double Mean;
	    			double probi=1.0;
    				Mean=countlblPro.getMean(elements, i);
    				Sdev=countlblPro.getSdev(elements, i);
    				if(Sdev!=0)
    				{
    					probi*=((1/(Math.sqrt(2*Math.PI)*Sdev))*(Math.exp(-(features[i]-Mean)*(features[i]-Mean)/(2*Sdev*Sdev))));
    	    			pro.add(probi);
    				}
    				else
    					pro.add(1.5);
    				
	        	}
			}
			for(double pi:pro)
				probility*=pi; //�����и������
			probility*=lblProba.get(index);//����ٳ���һ�� Yi
			pro_.add(probility);// ����pro_ ���� һ��ѭ��������	
			index++;
		}
		double max_pro=pro_.get(0);
		max_index=0;
		
		
		for(int i=1;i<pro_.size();i++)
		{
			if(pro_.get(i)>=max_pro)
			{
				max_pro=pro_.get(i);
				max_index=i;
			}	
		}  
		return  lblClass.get(max_index);
    }
    
    
    
    
    public class CountProbility
    {
    	boolean []isCatory;
    	double[][]features;
    	private double[]labels;
    	public CountProbility(boolean[] isCategory, double[][] features, double[] labels)
    	{
    		this.isCatory=isCategory;
    		this.features=features;
    		this.labels=labels;
    	}
    	//��ȡlabel��ȡֵ���
    	public void getlblClass(  ArrayList <Double>lblClass,ArrayList<Integer>lblCount,ArrayList<Float>lblProba)
    	{
    		int j=0;
            for(double i:labels)
            {
            	//�����ǰ��label��������lblClass�����
            	if(!lblClass.contains(i))
            	{
            		lblClass.add(j,i);
            		lblCount.add(j++,1);
            	}
            	else //���label���Ѿ����ڣ��ͽ��������1
            	{
            		int index=lblClass.indexOf(i); 
            		int count=lblCount.get(index);
            		lblCount.set(index,++count);
            	}
            		
            }
            for(int i=0;i<lblClass.size();i++)
            {
//            	System.out.println("ֵΪ"+lblClass.get(i)+"�ĸ�����"+lblCount.get(i)+"������"+lblCount.get(i)/(float)labels.length);
            	lblProba.add(i,lblCount.get(i)/(float)labels.length);
            }
            
    	}
    	//��label[]��features[][]�ϲ�
    	public ArrayList<ArrayList<Double>>  UnionFeaLbl(double[][] features, double[] labels)
    	{
    		ArrayList<ArrayList<Double>>traingList=new  ArrayList<ArrayList<Double>>();
    		for(int i=0;i<features.length;i++)
    		{
    			ArrayList<Double>elements=new ArrayList<Double>();
    			for(int j=0;j<features[i].length;j++)
    			{
    				elements.add(j,features[i][j]);
    			}
    			elements.add(features[i].length,labels[i]);
    			traingList.add(i,elements);
    			
    		}
    		return traingList;
    	}
    	/*���������鰴label��ֵ����洢*/
    	public ArrayList<ArrayList<ArrayList<Double>>> getClassListBasedLabel (ArrayList <Double>lblClass,ArrayList<ArrayList<Double>>trainingList)
    	{
    		ArrayList<ArrayList<ArrayList<Double>>> ClassListBasedLabel=new ArrayList<ArrayList<ArrayList<Double>>> () ;
        		for(double num:lblClass)
        		{
    				ArrayList<ArrayList<Double>> elements=new ArrayList<ArrayList<Double>>();
	    			for(ArrayList<Double>element:trainingList)
	    			{
	    				if(element.get(element.size()-1).equals(num))
	    					elements.add(element);
	    			}
    			ClassListBasedLabel.add(elements);
        		}
    			return ClassListBasedLabel;
    	}
    	public double getMean(ArrayList<ArrayList<Double>> elements,int index)
    	{
    		double sum=0.0;
    		double Mean;
    		
    		for(ArrayList<Double> element:elements)
    		{
    			sum+=element.get(index);
    			
    		}
    		Mean=sum/(double)elements.size();
    		return  Mean;
    	}
    	public double getSdev(ArrayList<ArrayList<Double>> elements,int index)
    	{
    		double dev=0.0;
    		double Mean;
    		Mean=getMean(elements,index);
    		for(ArrayList<Double> element:elements)
    		{
    			dev+=Math.pow((element.get(index)-Mean),2);
    		}
    		dev=Math.sqrt(dev/elements.size());
    		return  dev;
    	}
    	
    	
    }
}

