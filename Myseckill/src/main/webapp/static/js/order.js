/**
 * 
 */



//Vue.http.options.emulateJSON = true;

new Vue({
  el: '#app-1',
  data () {
    return {
         
      message: '',
      ruleForm: {
    	  itemid: '',
          address: '',
          message:'',
          phone: '',
          name: '',
          username:'',
          delivery: true,
          number: 1,
          price: ''
        
      },
      rules: {
        name: [
          { required: true, message: '请输入您的名字', trigger: 'blur' }
             ],
       
      address: [
          { required: true, message: '请选择配送地址', trigger: 'change' }
        ],
     
      }
    }
  },
  
  created () {
	  this.ruleForm.number=1;
	  this.ruleForm.price = decodeURIComponent((new RegExp('[?|&]' + 'itemprice' + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [''])[1].replace(/\+/g ,'%20')) || null;
	  this.ruleForm.itemid = decodeURIComponent((new RegExp('[?|&]' + 'id' + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [''])[1].replace(/\+/g ,'%20')) || null;
	  //alert( this.ruleForm.itemid );
	  this.message ='您要购买的商品是:'+ decodeURIComponent((new RegExp('[?|&]' + 'itemtitle' + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [''])[1].replace(/\+/g ,'%20')) || null+' 商品ID'+
	  decodeURIComponent((new RegExp('[?|&]' + 'id' + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [''])[1].replace(/\+/g ,'%20')) || null;;
	  //alert( this.message);
  } ,
  methods: {
	      //获取url参数
	  
    submitForm (formName) {
    	 alert('下单成功。您的订单将马上处理！')
    	//alert(cookie.get("username"));
    	 this.ruleForm.username=cookie.get("username");
    	 this.$http.post('/orderSubmit.do', this.ruleForm).then(result => {
             if(result.body.success){
            	 window.location.href="/index.html";
             }else{
            	  this.$emit(
                          'submit-form',
                          this.$message({
                              message: '提交失败',
                              type: 'warning',
                              duration: 6000
                          }),
                      );
             }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    btn_add(index) {
    	
		this.ruleForm.number+=1;
	},
	//减去
	btn_minute(index) {
		if(this.ruleForm.number>0)
		this.ruleForm.number-=1;
	},

  }

})