package web.mybatis.domain;

import java.util.Date;

public class BtsSfcDelivery {
	 //工厂
    private  String plant;
   
	 //单据类别
    private  String billtypename;
   
	 //单据号
    private  String vbillcode;
   
	 //发货单行项目号
    private  String vbillrowno;
   
	 //sfc号码
    private  String messfccode;
   
	 //物料编码
    private  String mesmaterial;
   
	 //物料版本
    private  String mesmaterialversion;
   
	 //生产订单号
    private  String mesprodorder;
   
	 //客户订单号
    private  String mescusorder;
   
	 //客户订单行项目号
    private  String mescusorderitem;
   
	 //客户编码
    private  String mescuscode;
   
	 //状态
    private  String status;
   
	 //创建时间
    private  Date createtime;
   
	 //创建用户
    private  String createuser;
   
	 //修改时间
    private  Date changetime;
   
	 //修改用户
    private  String changeuser;
   
    
    public  String  getPlant(){
    		return  this.plant;
    };
    public  void  setPlant(String plant){
    	this.plant=plant;
    }  
   
    public  String  getBilltypename(){
    		return  this.billtypename;
    };
    public  void  setBilltypename(String billtypename){
    	this.billtypename=billtypename;
    }  
   
    public  String  getVbillcode(){
    		return  this.vbillcode;
    };
    public  void  setVbillcode(String vbillcode){
    	this.vbillcode=vbillcode;
    }  
   
    public  String  getVbillrowno(){
    		return  this.vbillrowno;
    };
    public  void  setVbillrowno(String vbillrowno){
    	this.vbillrowno=vbillrowno;
    }  
   
    public  String  getMessfccode(){
    		return  this.messfccode;
    };
    public  void  setMessfccode(String messfccode){
    	this.messfccode=messfccode;
    }  
   
    public  String  getMesmaterial(){
    		return  this.mesmaterial;
    };
    public  void  setMesmaterial(String mesmaterial){
    	this.mesmaterial=mesmaterial;
    }  
   
    public  String  getMesmaterialversion(){
    		return  this.mesmaterialversion;
    };
    public  void  setMesmaterialversion(String mesmaterialversion){
    	this.mesmaterialversion=mesmaterialversion;
    }  
   
    public  String  getMesprodorder(){
    		return  this.mesprodorder;
    };
    public  void  setMesprodorder(String mesprodorder){
    	this.mesprodorder=mesprodorder;
    }  
   
    public  String  getMescusorder(){
    		return  this.mescusorder;
    };
    public  void  setMescusorder(String mescusorder){
    	this.mescusorder=mescusorder;
    }  
   
    public  String  getMescusorderitem(){
    		return  this.mescusorderitem;
    };
    public  void  setMescusorderitem(String mescusorderitem){
    	this.mescusorderitem=mescusorderitem;
    }  
   
    public  String  getMescuscode(){
    		return  this.mescuscode;
    };
    public  void  setMescuscode(String mescuscode){
    	this.mescuscode=mescuscode;
    }  
   
    public  String  getStatus(){
    		return  this.status;
    };
    public  void  setStatus(String status){
    	this.status=status;
    }  
   
    public  Date  getCreatetime(){
    		return  this.createtime;
    };
    public  void  setCreatetime(Date createtime){
    	this.createtime=createtime;
    }  
   
    public  String  getCreateuser(){
    		return  this.createuser;
    };
    public  void  setCreateuser(String createuser){
    	this.createuser=createuser;
    }  
   
    public  Date  getChangetime(){
    		return  this.changetime;
    };
    public  void  setChangetime(Date changetime){
    	this.changetime=changetime;
    }  
   
    public  String  getChangeuser(){
    		return  this.changeuser;
    };
    public  void  setChangeuser(String changeuser){
    	this.changeuser=changeuser;
    }  
   
}
