class Testing {
    public void test(@RequestParam String value) {
        System.out.println("I'm testing");
      
        Cookie cookie = new Cookie("cookie", value);
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
    }
}
