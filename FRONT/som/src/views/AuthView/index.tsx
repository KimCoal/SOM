import { useState } from "react";
import { Box, Card, Grid, Typography } from "@mui/material";
import ContentPasteTwoToneIcon from "@mui/icons-material/ContentPasteTwoTone";
import LoginCardView from "./LoginCard";
import SignUpCardView from "./SignUpCard";

export default function AuthenticationView() {
  const [loginView, setLoginView] = useState<boolean>(true);

  return (
    <Box sx={{ 
      backgroundImage: `url("https://images.unsplash.com/photo-1679967488699-f159404b5c5c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1856&q=80")`,
      backgroundRepeat : "no-repeat",
      backgroundSize: 'inherit',
      backgroundColor: '#1B061F',
      backgroundPosition: '-8% 50%',
      height: "94vh",
      width: "100vw"
      }}>
      <Grid container>
      <Grid item lg={8} sm={12}>
          <Box
            sx={{
              display: "flex",
              height: "100%",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
          </Box>
        </Grid>
        <Grid item lg={3} sm={12}>
          <Card
            sx={{
              height: "630px",
              mt: "100px",
              mb: "80px",
              pt: "50px",
              pb: "30px",
              pl: "50px",
              pr: "50px",
              mr: "20px",
              ml: "20px",
            }}
          >
            {loginView ? (
              <LoginCardView setLoginView={setLoginView} />
            ) : (
              <SignUpCardView setLoginView={setLoginView} />
            )}
          </Card>
        </Grid>
      </Grid>
    </Box>
  );
}
