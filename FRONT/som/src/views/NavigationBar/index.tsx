import {
  AppBar,
  Box,
  Button,
  FormControl,
  IconButton,
  InputAdornment,
  OutlinedInput,
  Toolbar,
  Typography,
} from "@mui/material";
import { useState, KeyboardEvent } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useUserStore } from "src/stores";
import SearchIcon from "@mui/icons-material/Search";

export default function NavigationBar() {
  const navigator = useNavigate();
  const path = useLocation();

  const { user } = useUserStore();

  const [content, setContent] = useState<string>("");

  const onSearchKeyPressHandler = (event: KeyboardEvent<HTMLDivElement>) => {
    if (event.key !== "Enter") return;
    onSearchHandler();
  };

  const onSearchHandler = () => {
    if (!content.trim()) {
      alert("검색어를 입력해주세요.");
      return;
    }
    navigator("/board/search/${content}");
  };
  return (
    <Box>
      <AppBar
        variant="outlined"
        position="static"
        sx={{ p: "0px 120px", backgroundColor: "#FF5500" }}
      >
        <Toolbar>
          <Typography
            variant="h5"
            noWrap
            component="div"
            sx={{

              flexGrow: 1,
              display: { xs: "none", sm: "block", color: "white" },
            }}
            onClick={() => navigator("/")}
          >
            SOM
          </Typography>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ mr: "20px",
              fontFamily: "nanumgothic",
              display: { xs: "none", sm: "block", color: "white" },
            }}
            onClick={() => navigator("/board/categorise/Kr")}
          >
            KR
          </Typography>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ mr: "20px",
              fontFamily: "nanumgothic",
              display: { xs: "none", sm: "block", color: "white" },
            }}
            onClick={() => navigator("/board/categorise/Pop")}
          >
            POP
          </Typography>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ mr: "20px",
              fontFamily: "nanumgothic",
              display: { xs: "none", sm: "block", color: "white" },
            }}
            onClick={() => navigator("/board/categorise/Else")}
          >
            Else
          </Typography>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ mr: "20px",
              fontFamily: "nanumgothic",
              display: { xs: "none", sm: "block", color: "white" },
            }}
            onClick={() => navigator("/board/categorise/정보")}
          >
            Info
          </Typography>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ mr: "20px",
              fontFamily: "nanumgothic",
              display: { xs: "none", sm: "block", color: "white" },
            }}
            onClick={() => navigator("/board/categorise/자유게시판")}
          >
            Free
          </Typography>
          <Box sx={{ display: "flex" }}>
            <FormControl variant="outlined" sx={{ mr: "10px", color: "white" }}>
              <OutlinedInput
                size="small"
                type="text"
                placeholder="검색어를 입력해주세요."
                endAdornment={
                  <InputAdornment position="end">
                    <IconButton edge="end" onClick={onSearchHandler}>
                      <SearchIcon />
                    </IconButton>
                  </InputAdornment>
                }
                onChange={(event) => setContent(event.target.value)}
                onKeyPress={(event) => onSearchKeyPressHandler(event)}
              />
            </FormControl>
            {path.pathname !== "/auth" &&
              (user ? (
                <Button
                  variant="contained"
                  sx={{
                    backgroundColor: "#343a40",
                    ":hover": { backgroundColor: "#FF5500" },
                  }}
                  onClick={() => navigator("/myPage")}
                >
                  마이페이지
                </Button>
              ) : (
                <Button
                  variant="contained"
                  sx={{
                    backgroundColor: "#343a40",
                    ":hover": { backgroundColor: "#FF5500" },
                  }}
                  onClick={() => navigator("/auth")}
                >
                  로그인
                </Button>
              ))}
          </Box>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
