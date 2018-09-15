package web.mybatis.domain;

import java.util.Date;

public class BtsMetrialInfo {
	
	private  String handle;
    
	 //工厂
     private  String plant;
    
	 //物料编码
     private  String material;
    
	 //物料描述
     private  String matdescription;
    
	 //储能装置中的编号\n对应吉利API serial字段
     private  String systemcode;
    
	 //电池包型号\n对应吉利API modelId字段
     private  String packmodle;
    
	 //所属储能装置编码
     private  String systemid;
    
	 //储能装置型号
     private  String systemmodelid;
    
	 //模块型号
     private  String modelid;
    
	 //单体型号
     private  String cellmodelid;
    
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
    
     
     public  String  getHandle(){
     		return  this.handle;
     };
     public  void  setHandle(String handle){
     	this.handle=handle;
     }  
    
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
    
     public  String  getSystemcode(){
     		return  this.systemcode;
     };
     public  void  setSystemcode(String systemcode){
     	this.systemcode=systemcode;
     }  
    
     public  String  getPackmodle(){
     		return  this.packmodle;
     };
     public  void  setPackmodle(String packmodle){
     	this.packmodle=packmodle;
     }  
    
     public  String  getSystemid(){
     		return  this.systemid;
     };
     public  void  setSystemid(String systemid){
     	this.systemid=systemid;
     }  
    
     public  String  getSystemmodelid(){
     		return  this.systemmodelid;
     };
     public  void  setSystemmodelid(String systemmodelid){
     	this.systemmodelid=systemmodelid;
     }  
    
     public  String  getModelid(){
     		return  this.modelid;
     };
     public  void  setModelid(String modelid){
     	this.modelid=modelid;
     }  
    
     public  String  getCellmodelid(){
     		return  this.cellmodelid;
     };
     public  void  setCellmodelid(String cellmodelid){
     	this.cellmodelid=cellmodelid;
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
