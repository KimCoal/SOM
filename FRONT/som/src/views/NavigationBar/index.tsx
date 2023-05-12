import { useState, KeyboardEvent } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import { useUserStore } from "src/stores";

export default function NavtgationBar() {

  const navigator = useNavigate();
  const path = useLocation();

  const { user } = useUserStore();

  const [content, setContent] = useState<string>('');
  return (
    <div>index</div>
  );
}
