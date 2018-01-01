var dateFormatter = function(dateString) {
  var date = new Date(dateString);
  var monthString = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
  var month = monthString[date.getMonth()];
    return month+" "+twoDigits(date.getDate())+", "+date.getFullYear()+" "+twoDigits(date.getHours())+":"+twoDigits(date.getMinutes())+":"+twoDigits(date.getSeconds());
}

var twoDigits = function(data){
  if(data>=10){
    return data;
  }else{
    return '0'+data;
  }
}
