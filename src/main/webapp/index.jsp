<html>
<body>
<h2>Hello World!</h2>
<button onclick="testpost()">testpost</button>
<button onclick="testget()">testget</button>
</body>
<script src="https://cdn.bootcss.com/axios/0.18.0/axios.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/2.2.4/jquery.js"></script> 
<script type="text/javascript">

/*  axios.post('user/login', {
	"username" : "zwb",
	"password" : "888888"
  }).then(function (response) {
    console.log(response);
  }).catch(function (error) {
    console.log(error);
  });  */
	/*  axios.get('user/login', {
	    params: {
	    	"username" : "zwb",
			"password" : "888888"
	    }
	  }).then(function (response) {
	    console.log(response);
	  }).catch(function (error) {
	    console.log(error);
	  });  */
/*   $(function(){
	 $.ajax({
		url : 'user/download',
		method : 'get',
		success: function(rep){
		// console.log(rep);
	 }
		
	});
	
})    */
window.open("http://localhost:8080/zwb/user/download");
</script>
</html>
