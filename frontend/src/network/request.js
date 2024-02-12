import Axios from "axios";
import { REQUEST_BASE_URL_DEV, REQUEST_BASE_URL_PROD } from "@/common/const";

const instanceNormal = () => {
  let token = sessionStorage.getItem("token");
  let headers = token ? {
    "Authorization": sessionStorage.getItem("token")
  } : {};
  return Axios.create({
    // baseURL:
    //   process.env.NODE_ENV === "production"
    //     ? REQUEST_BASE_URL_PROD
    //     : REQUEST_BASE_URL_DEV,
    headers
  });
}

const _get = (url, config) => {
  return new Promise((resolve, reject) => {
    instanceNormal()
      .get(url, config)
      .then(data => {
        resolve(data.data);
      })
      .catch(err => {
        reject(err);
      });
  });
};
const _post = (url, config) => {
  return new Promise((resolve, reject) => {
    instanceNormal()
      .post(url, config)
      .then(data => {
        resolve(data.data);
      })
      .catch(err => {
        reject(err);
      });
  });
};
const _delete = (url, config) => {
  return new Promise((resolve, reject) => {
    instanceNormal()
      .delete(url, config)
      .then(data => {
        resolve(data.data);
      })
      .catch(err => {
        reject(err);
      });
  });
};
const _put = (url, config) => {
  return new Promise((resolve, reject) => {
    instanceNormal()
      .put(url, config)
      .then(data => {
        resolve(data.data);
      })
      .catch(err => {
        reject(err);
      });
  });
};

const _download = (url, config)=>{
  return new Promise((resolve, reject) => {
    console.log(url,config)
    instanceNormal()
      .post(url, config, { responseType: 'blob' })
      .then(_res => {
        console.log("73")
        const { data, headers } = _res
        const fileName = headers['content-disposition'].replace(/\w+;filename=(.*)/, '$1')
        // const blob = new Blob([data], { type: headers['content-type'] })
        const blob = new Blob([data], { type: 'application/vnd.ms-excel' })
        console.log("blob", blob)
        let dom = document.createElement('a')
        let url = window.URL.createObjectURL(blob)
        dom.href = url
        dom.download = decodeURI(fileName)
        dom.style.display = 'none'
        document.body.appendChild(dom)
        dom.click()
        dom.parentNode.removeChild(dom)
        window.URL.revokeObjectURL(url)
      })
      .catch(err => {
        console.log(err)
      });
  });
}

export default {
  _get,
  _post,
  _delete,
  _put,
  _download
};
