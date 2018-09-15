package web.mybatis.domain;

public class BtsPackModule {
	 //handle
    private  String modulehandle;
   
	 //Pack HANDLE
    private  String packhandle;
   
	 //PACK序列号
    private  String packcode;
   
	 //模块序列号
    private  String modulecode;
   
	 //模块物料号
    private  String modulematerial;
   
	 //状态
    private  String status;
   
    
    public  String  getModulehandle(){
    		return  this.modulehandle;
    };
    public  void  setModulehandle(String modulehandle){
    	this.modulehandle=modulehandle;
    }  
   
    public  String  getPackhandle(){
    		return  this.packhandle;
    };
    public  void  setPackhandle(String packhandle){
    	this.packhandle=packhandle;
    }  
   
    public  String  getPackcode(){
    		return  this.packcode;
    };
    public  void  setPackcode(String packcode){
    	this.packcode=packcode;
    }  
   
    public  String  getModulecode(){
    		return  this.modulecode;
    };
    public  void  setModulecode(String modulecode){
    	this.modulecode=modulecode;
    }  
   
    public  String  getModulematerial(){
    		return  this.modulematerial;
    };
    public  void  setModulematerial(String modulematerial){
    	this.modulematerial=modulematerial;
    }  
   
    public  String  getStatus(){
    		return  this.status;
    };
    public  void  setStatus(String status){
    	this.status=status;
    }  
}
