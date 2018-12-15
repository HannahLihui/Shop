<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<style>
body{
background-image: url('/static/image/shoplg4.jpg')  ;
 background-repeat: no-repeat;
  

 
}
</style>
<%String username=(String)request.getSession().getAttribute("username"); %>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>

  <form class="form-horizontal" action="/orderSubmit.do" method="post">
        <div class="container">
            <div class="row" style="padding: 20px 0">
                <h3 style="color:#fff">商品下单</h3>
            </div>
           <input type="hidden" id="username" name="username" value="<%=username%>" />
           <input type="hidden" id="itemid" name="itemid" value=""/>
            <div class="row form-group">
                <label class="control-label col-lg-1" for="name" style="color:#fff">商品详情</label>
                <div class="col-lg-5 col-md-6">
                    <span class="form-control"  id="detail"  style="background-color: #DEDEDE;">商品详细描述</span>
                </div>
            </div>
            <div class="row form-group">
                <label class="control-label col-lg-1" style="color:#fff">商品价格</label>
                <div class="col-lg-5 col-md-6">
                    <span id="price" name="price" class="form-control" rows="1" style="width:100px; background-color: #DEDEDE;"></span>
                   
                </div>
                 <label class="control-label col-lg-1" style="color:#fff">商品数量</label>
                <div class="col-lg-5 col-md-6">
                    <input id="number" name="number" placeholder="1" class="form-control"  style="width:80px;">
                   
                </div>
            </div>
            <div class="row form-group">
                <label class="control-label col-lg-1" style="color:#fff">备注信息</label>
                <div class="col-lg-5 col-md-6">
                    <textarea class="form-control" rows="3" ></textarea>
                </div>
            </div>
              <div class="row form-group">
                <label class="control-label col-lg-1" style="color:#fff">联系电话</label>
                <div class="col-lg-5 col-md-6">
                    <textarea class="form-control" rows="1" name="phone"></textarea>
                </div>
            </div>
              <div class="row form-group">
                <label class="control-label col-lg-1" style="color:#fff">地址</label>
                <div class="col-lg-5 col-md-6">
                    <textarea class="form-control" rows="2"></textarea>
                </div>
            </div>
        </div>
         <div class="row form-group">
               
                <div class="col-lg-5 col-md-6">
                    <button type="submit" >提交</button>
                </div>
            </div>
       
       
       
    </form>

              
                

</body>
<script>
window.onload=function(){

	 var name,value;
	   var str=location.href; //取得整个地址栏
	   var num=str.indexOf("?")
	   str=str.substr(num+1); //取得所有参数   stringvar.substr(start [, length ]

	   var arr=str.split("&"); //各个参数放到数组里
	    console.log(arr)
	   for(var i=0;i < arr.length;i++){
	        num=arr[i].indexOf("=");
	        if(num>0){
	             name=arr[i].substring(0,num);
	             value=arr[i].substr(num+1);
	             this[name]=value;
	        }
	   }
	    $("#itemid").val(this['id']);
		  console.log($("#itemid").val);
	  
	       var questionnaireName = GetUrlByParamName("itemtitle");
	        var title=document.getElementById("detail");
	   title.innerHTML=questionnaireName;
	   var price=document.getElementById("price");
	   price.innerHTML=this['itemprice']+'元';
	  
	   
	}
function GetUrlByParamName(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var URL =  decodeURI(window.location.search);
        var r = URL.substr(1).match(reg);
        if(r!=null){
            //decodeURI() 函数可对 encodeURI() 函数编码过的 URI 进行解码
            return  decodeURI(r[2]);
        };
        return null;
    };
    //页面加载后立即执行




</script>
 <script src="bootstrap-3.3.0/jquery.js"></script>
    <link href="bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-3.3.0/bootstrap.min.js"></script>
</html>