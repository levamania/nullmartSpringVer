export default function toWon(price){
		if(typeof price=="number"){
			price = price.toString();
		}
	
		var arr = new Array();
		for(var i=0;i<price.length;i++){
			arr.push(price.charAt(i));
		}
		for(var i=1;price.length-i*3>0;i++){
			arr.splice(price.length-i*3,1,","+arr[price.length-i*3]);
		}
		var string ="";
		for(var i of arr){
			string+=i;
		}	
		return string; 
	}

//숫자변환 함수
export function toNum(price){
	var regEx = /\d{1,100}/g;
	var x = price.match(regEx);
	var string = "";
	for(var i of x){
		string+=i;
	}
	return Number.parseInt(string);
}