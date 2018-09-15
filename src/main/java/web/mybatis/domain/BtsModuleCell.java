package web.mybatis.domain;

public class BtsModuleCell {
	 //HANDLE
    private  String cellhandle;
   
	 //模块对应的handle
    private  String modulehandle;
   
	 //模块序列号
    private  String modulecode;
   
	 //电芯序列号
    private  String cellcode;
   
	 //电芯物料号
    private  String cellmaterial;
   
	 //状态\n10新增\n20 删除\n30 已上传\n
    private  String status;
   
    
    public  String  getCellhandle(){
    		return  this.cellhandle;
    };
    public  void  setCellhandle(String cellhandle){
    	this.cellhandle=cellhandle;
    }  
   
    public  String  getModulehandle(){
    		return  this.modulehandle;
    };
    public  void  setModulehandle(String modulehandle){
    	this.modulehandle=modulehandle;
    }  
   
    public  String  getModulecode(){
    		return  this.modulecode;
    };
    public  void  setModulecode(String modulecode){
    	this.modulecode=modulecode;
    }  
   
    public  String  getCellcode(){
    		return  this.cellcode;
    };
    public  void  setCellcode(String cellcode){
    	this.cellcode=cellcode;
    }  
   
    public  String  getCellmaterial(){
    		return  this.cellmaterial;
    };
    public  void  setCellmaterial(String cellmaterial){
    	this.cellmaterial=cellmaterial;
    }  
   
    public  String  getStatus(){
    		return  this.status;
    };
    public  void  setStatus(String status){
    	this.status=status;
    }  
}
