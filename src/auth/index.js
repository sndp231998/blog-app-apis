//1..isLoggedIn=> logic=(token xa && user ko details pani xa vane .. authenticate vayo)

export const isLoggedIn=()=>{
    let data=localStorage.getItem("data");
    if(data!=null) return true;
    else return false;
    
};


//2..doLogin => jun data hami paxs garinxa , teo data local storage ma save 
export const doLogin=(data,next)=>{
    localStorage.setItem("data",JSON.stringify(data));
next()
};


//3..doLogout ==> remove from locals storage

export const doLogout=(next)=>{
    localStorage.removeItem("data");
    next()
};

//4. get currrent user
export const getCurrentUserDetail=()=>{
if(isLoggedIn ()){
    return JSON.parse(localStorage.getItem("data")).user;
}else{
    return undefined;
}
};

export const getToken=()=>{
    if(isLoggedIn()){
      return JSON.parse(localStorage.getItem("data")).token
    }else{
      return null;
    }
  }