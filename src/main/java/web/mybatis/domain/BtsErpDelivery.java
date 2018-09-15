package web.mybatis.domain;

import java.util.Date;

public class BtsErpDelivery {
	 //工厂
    private  String plant;
   
	 //物料编码
    private  String material;
   
	 //物料描述
    private  String matdescription;
   
	 //物料规格
    private  String matspec;
   
	 //客户编码
    private  String cuscode;
   
	 //客户名称
    private  String cusdescription;
   
	 //单据类别
    private  String billtypename;
   
	 //单据号
    private  String vbillcode;
   
	 //单据日期
    private  Date vbilldate;
   
	 //发货类型
    private  String vbilldtype;
   
	 //发货计划员
    private  String vbilldpname;
   
	 //发货部门
    private  String vbillddname;
   
	 //锂电订单号
    private  String vbillvdef2;
   
	 //发货单行项目号
    private  String vbillrowno;
   
	 //主数量
    private  String vbilldnum;
   
	 //主单位
    private  String vbillduname;
   
	 //已关联条码数量
    private  String deliverynum;
   
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
   
    public  String  getMaterial(){
    		return  this.material;
    };
    public  void  setMaterial(String material){
    	this.material=material;
    }  
   
    public  String  getMatdescription(){
    		return  this.matdescription;
    };
    public  void  setMatdescription(String matdescription){
    	this.matdescription=matdescription;
    }  
   
    public  String  getMatspec(){
    		return  this.matspec;
    };
    public  void  setMatspec(String matspec){
    	this.matspec=matspec;
    }  
   
    public  String  getCuscode(){
    		return  this.cuscode;
    };
    public  void  setCuscode(String cuscode){
    	this.cuscode=cuscode;
    }  
   
    public  String  getCusdescription(){
    		return  this.cusdescription;
    };
    public  void  setCusdescription(String cusdescription){
    	this.cusdescription=cusdescription;
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
   
    public  Date  getVbilldate(){
    		return  this.vbilldate;
    };
    public  void  setVbilldate(Date vbilldate){
    	this.vbilldate=vbilldate;
    }  
   
    public  String  getVbilldtype(){
    		return  this.vbilldtype;
    };
    public  void  setVbilldtype(String vbilldtype){
    	this.vbilldtype=vbilldtype;
    }  
   
    public  String  getVbilldpname(){
    		return  this.vbilldpname;
    };
    public  void  setVbilldpname(String vbilldpname){
    	this.vbilldpname=vbilldpname;
    }  
   
    public  String  getVbillddname(){
    		return  this.vbillddname;
    };
    public  void  setVbillddname(String vbillddname){
    	this.vbillddname=vbillddname;
    }  
   
    public  String  getVbillvdef2(){
    		return  this.vbillvdef2;
    };
    public  void  setVbillvdef2(String vbillvdef2){
    	this.vbillvdef2=vbillvdef2;
    }  
   
    public  String  getVbillrowno(){
    		return  this.vbillrowno;
    };
    public  void  setVbillrowno(String vbillrowno){
    	this.vbillrowno=vbillrowno;
    }  
   
    public  String  getVbilldnum(){
    		return  this.vbilldnum;
    };
    public  void  setVbilldnum(String vbilldnum){
    	this.vbilldnum=vbilldnum;
    }  
   
    public  String  getVbillduname(){
    		return  this.vbillduname;
    };
    public  void  setVbillduname(String vbillduname){
    	this.vbillduname=vbillduname;
    }  
   
    public  String  getDeliverynum(){
    		return  this.deliverynum;
    };
    public  void  setDeliverynum(String deliverynum){
    	this.deliverynum=deliverynum;
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
