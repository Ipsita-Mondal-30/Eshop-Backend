@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    // Authenticate admin user
    public boolean authenticateAdmin(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password) && user.isAdmin();
    }

    // Register admin user
    public boolean registerAdmin(User userRequest) {
        if (userRequest != null) {
            userRequest.setAdmin(true); // Mark the user as an admin
            userRepository.save(userRequest); // Save user to the database
            return true;
        }
        return false;
    }
}
