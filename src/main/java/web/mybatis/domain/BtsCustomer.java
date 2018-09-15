package web.mybatis.domain;

import java.util.Date;

public class BtsCustomer {
	 //工厂
    private  String plant;
   
	 //客户编码
    private  String custormercode;
   
	 //客户描述
    private  String custormerdescrip;
   
	 //集成方式
    private  String inttype;
   
	 //数据传输地址
    private  String urlorftp;
   
	 //数据传输账号
    private  String userortoken;
   
	 //数据传输密码
    private  String userpwdorkey;
   
	 //文件名前缀
    private  String filenameprfix;
   
	 //供应商代码
    private  String salescode;
   
	 //最后一次上传日期
    private  Date lastloaddate;
   
	 //当日传输序号
    private  String lastfileid;
   
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
   
    public  String  getCustormercode(){
    		return  this.custormercode;
    };
    public  void  setCustormercode(String custormercode){
    	this.custormercode=custormercode;
    }  
   
    public  String  getCustormerdescrip(){
    		return  this.custormerdescrip;
    };
    public  void  setCustormerdescrip(String custormerdescrip){
    	this.custormerdescrip=custormerdescrip;
    }  
   
    public  String  getInttype(){
    		return  this.inttype;
    };
    public  void  setInttype(String inttype){
    	this.inttype=inttype;
    }  
   
    public  String  getUrlorftp(){
    		return  this.urlorftp;
    };
    public  void  setUrlorftp(String urlorftp){
    	this.urlorftp=urlorftp;
    }  
   
    public  String  getUserortoken(){
    		return  this.userortoken;
    };
    public  void  setUserortoken(String userortoken){
    	this.userortoken=userortoken;
    }  
   
    public  String  getUserpwdorkey(){
    		return  this.userpwdorkey;
    };
    public  void  setUserpwdorkey(String userpwdorkey){
    	this.userpwdorkey=userpwdorkey;
    }  
   
    public  String  getFilenameprfix(){
    		return  this.filenameprfix;
    };
    public  void  setFilenameprfix(String filenameprfix){
    	this.filenameprfix=filenameprfix;
    }  
   
    public  String  getSalescode(){
    		return  this.salescode;
    };
    public  void  setSalescode(String salescode){
    	this.salescode=salescode;
    }  
   
    public  Date  getLastloaddate(){
    		return  this.lastloaddate;
    };
    public  void  setLastloaddate(Date lastloaddate){
    	this.lastloaddate=lastloaddate;
    }  
   
    public  String  getLastfileid(){
    		return  this.lastfileid;
    };
    public  void  setLastfileid(String lastfileid){
    	this.lastfileid=lastfileid;
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
