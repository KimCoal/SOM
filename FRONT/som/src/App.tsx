import './App.css';

import { useCookies } from 'react-cookie';
import { useUserStore } from './stores';
import { useLocation } from 'react-router-dom';
import axios, { AxiosResponse } from 'axios';
import { GET_USER_URL, authorizationHeader } from './constants/api';
import ResponseDto from './apis/response';

function App() {

  const path = useLocation();
  const { setUser } = useUserStore();
  const [ cookies ] = useCookies();

  const getUser = (accessToken: string) => {
    axios.get(GET_USER_URL, authorizationHeader(accessToken))
      .then((response) => getUserResponseHandler(response))
      .catch((error) => getUserErrorHandler(error));
  }

  const getUserResponseHandler = (response: AxiosResponse<any, any>) => {
    const { result, message, data } = response.data as ResponseDto<any>;
    if (!result || !data) {
      return;
    }
    const user = data as GetUserResponseDto;
    setUser(user);
  }

  const getUserErrorHandler = (error: any) => {
    console.log(error.message);
  }

  useEffect(() => {
    const accessToken = cookies.accessToken;
    if (accessToken) getUser(accessToken);
  }, [path]);

  return (
    <div className="App">
      <header className="App-header">
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
