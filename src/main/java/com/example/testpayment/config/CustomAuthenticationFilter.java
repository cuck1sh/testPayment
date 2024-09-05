package com.example.testpayment.config;

//@Component
//@RequiredArgsConstructor
//public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final UserService userService;
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String username = request.getParameter("email");
//        if (userService.isBlocked(username)) {
//            throw new LockedException("Аккаунт временно заблокирован из-за множества неудачных попыток входа");
//        }
//        return super.attemptAuthentication(request, response);
//    }
//}