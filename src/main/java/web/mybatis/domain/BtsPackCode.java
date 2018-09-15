package web.mybatis.domain;

import java.util.Date;

public class BtsPackCode {
	//PACK唯一索引序列号
    private  String packhandle;
   
	 //工厂
    private  String plant;
   
	 //PACK序列号
    private  String packcode;
   
	 //PACK物料编码
    private  String packmaterial;
   
	 //PACK订单号
    private  String packorder;
   
	 //10新增\n20 删除\n30 已上传\n
    private  String status;
   
	 //日期时间
    private  Date createtime;
   
	 //创建用户
    private  String createuser;
   
	 //修改时间
    private  Date changetime;
   
	 //修改用户
    private  String changeuser;
   
	 //上传时间
    private  Date uploadtime;
   
	 //上传用户
    private  String uploaduser;
   
	 //发货单
    private  String parentsfccode;
   
    
    public  String  getPackhandle(){
    		return  this.packhandle;
    };
    public  void  setPackhandle(String packhandle){
    	this.packhandle=packhandle;
    }  
   
    public  String  getPlant(){
    		return  this.plant;
    };
    public  void  setPlant(String plant){
    	this.plant=plant;
    }  
   
    public  String  getPackcode(){
    		return  this.packcode;
    };
    public  void  setPackcode(String packcode){
    	this.packcode=packcode;
    }  
   
    public  String  getPackmaterial(){
    		return  this.packmaterial;
    };
    public  void  setPackmaterial(String packmaterial){
    	this.packmaterial=packmaterial;
    }  
   
    public  String  getPackorder(){
    		return  this.packorder;
    };
    public  void  setPackorder(String packorder){
    	this.packorder=packorder;
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
   
    public  Date  getUploadtime(){
    		return  this.uploadtime;
    };
    public  void  setUploadtime(Date uploadtime){
    	this.uploadtime=uploadtime;
    }  
   
    public  String  getUploaduser(){
    		return  this.uploaduser;
    };
    public  void  setUploaduser(String uploaduser){
    	this.uploaduser=uploaduser;
    }  
   
    public  String  getParentsfccode(){
    		return  this.parentsfccode;
    };
    public  void  setParentsfccode(String parentsfccode){
    	this.parentsfccode=parentsfccode;
    }  
}
