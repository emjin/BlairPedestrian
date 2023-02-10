class Testing {
    void test(@RequestParam String value, HttpServletResponse response) {
        System.out.println("Why even print things?");
    
        Cookie cookie = new Cookie("cookie", value);
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
    }
}
