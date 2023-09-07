import axios from "axios";
import queryString from "query-string";

const BASE_URL = "http://localhost:8080/api/v1";
// const BASE_URL = "https://api-generator.retool.com";

const instance = axios.create({
  baseURL: BASE_URL,
  timeout: 10000,
});

instance.defaults.withCredentials = true;

instance.interceptors.request.use(
  function (config) {
    // Do something before request is sent
    const token = localStorage.getItem("token");
    console.log(`tokenn`, token);
    if(token) {
      config.headers.Authorization = "Bearer " + token; //cấu hình token cho all request

    }
    return config;
  },
  function (error) {
    // Do something with request error
    console.log("request Error", error);
    return Promise.reject(error);
  }
);

instance.interceptors.response.use(
  function (response) {
    return response.data;
  },
  function (error) {
    console.log("error config", error);
    return Promise.reject(error?.response?.data);
  }
);

// eslint-disable-next-line @typescript-eslint/no-explicit-any
function queryStringData(url: string, params: any) {
  return queryString.stringifyUrl({ url: url, query: params });
}
export const typeFormData = {
    headers: { "Content-Type": "multipart/form-data" },
  };
  

//nếu truyền formData thì ta pass thêm 1 tham số thứ 3 vào method là typeFormData thôi, nó sẽ tự ghi đè application/json
//VD: post: (url: string, payload?: unknown) => instance.post(url, payload, typeFormData),
export const Apiclient = {
  get: (url: string, payload?: object) =>
    instance.get(queryStringData(url, payload)),
  post: (url: string, payload?: unknown) => instance.post(url, payload),
  patch: (url: string, payload?: unknown) => instance.patch(url, payload),
  put: (url: string, payload?: unknown) => instance.put(url, payload),
  delete: (url: string) => instance.delete(url),
  postFormData: (url: string, payload?: unknown) =>
    instance.post(url, payload, {
      headers: { "Content-Type": "multipart/form-data" },
    }),
};
