import IApi, {PageData, ApiResult, FetchRequest} from './IApi'
import cookie from 'react-cookies'


class BaseApi<M> implements IApi<M> {
  private readonly baseUrl: string;

  constructor(baseUrl?: string) {
    this.baseUrl = baseUrl;
  }

  public request(input: FetchRequest): Promise<any> {
    return fetch(input.url, {
      credentials: 'include',
      ...input
    })
      .then((r: Response) => BaseApi.json(r))
      .then(result => {
        console.log("fetch-log:", result);
        return result;
      })
      .then(BaseApi.check)
      .catch(e => {
        console.error("fetch-error:", e);
        throw e;
      })
  }

  public get(input: string | RequestInit, param?: {}): Promise<any> {
    let paramStr: string = (typeof input === typeof '' ? input + "?" : (input as Request).url) + "?";
    if (param) {
      for (let key in param) {
        paramStr += `${key}=${param[key]}&`
      }
      paramStr = paramStr.substring(0, paramStr.length - 1);
    }
    return this.request({
        url: paramStr,
        method: 'get'
      }
    );
  }

  public post(url: string, param?: {}, method?: string): Promise<any> {
    let formData: FormData = new FormData();
    if (param) {
      for (let key in param) {
        if (undefined === param[key] || param[key] === null || param[key] === '') {
          continue;
        }
        formData.append(key, param[key]);
      }
    }
    return this.request({
      url,
      method: method ? method : 'POST',
      body: formData
    });
  }

  postJson(url: string, data?: {}): Promise<any> {
    return this.request({
      url,
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }

  public put(url: string, param?: {}): Promise<boolean> {
    return this.post(url, param, 'PUT');
  }

  public delete(url: string, param?: {}): Promise<boolean> {
    return this.post(url, param, 'DELETE');
  }

  public save(model: M): Promise<M> {
    if (!this.baseUrl) {
      throw new Error("baseUrl is null")
    }
    return this.post(`${this.baseUrl}/create`, model)
  }

  public update(model: {
    id: number
  }): Promise<boolean> {
    if (!this.baseUrl) {
      throw new Error("baseUrl is null")
    }
    return this.put(`${this.baseUrl}/update`, model)
  }

  public deleteOne(id: number): Promise<boolean> {
    if (!this.baseUrl) {
      throw new Error("baseUrl is null")
    }
    return this.delete(`${this.baseUrl}/delete`, {
      id
    });
  }

  public deleteAny(ids: Array<number>): Promise<boolean> {
    if (!this.baseUrl) {
      throw new Error("baseUrl is null")
    }
    return this.delete(`${this.baseUrl}/deletes`, {
      ids
    });
  }

  public basePage(page: {
    pageNumber: number,
    pageSize: number,
    order?: {
      filed: string,
      asc?: boolean
    }
  }, param?: Object): Promise<PageData<M>> {
    if (!this.baseUrl) {
      throw new Error("baseUrl is null")
    }
    return this.post(`${this.baseUrl}/list`, {...page, ...param});
  }

  public getOneById(id: number): Promise<M> {
    if (!this.baseUrl) {
      throw new Error("baseUrl is null")
    }
    return this.get(`${this.baseUrl}/me`, {
      id
    });
  }

  private static check(result: ApiResult): any {
    if (!result.success) {
      return Promise.reject(result);
    }
    return result.data;
  }

  private static json(response: Response): Promise<ApiResult> {
    if (response.status >= 200 && response.status < 300) {
      try {
        return response.json();
      } catch (e) {
        throw e;
      }
    } else if (response.status === 401) {
      location.href = "/login"
    }
    throw {
      success: false,
      data: null,
      errorCode: "HTTP-ERROR",
      status: response.status,
      response
    };
  }
}

export default BaseApi;
