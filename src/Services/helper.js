import axios from "axios";
import { getToken } from "../auth";

//export const BASE_URL='http://localhost:9090/api/v1';
export const BASE_URL='https://divine-act-production.up.railway.app/api/v1';

export const myAxios=axios.create({
    baseURL:BASE_URL,
});


export const privateAxios=axios.create({
    baseURL:BASE_URL
})

privateAxios.interceptors.request.use(
    (config)=>{

    const token=getToken();
    console.log(token);
    if(token){
        config.headers.common.Authorization=`bearer ${token}`;
        
    }
    return config;
},
(error)=>Promise.reject(error)
);