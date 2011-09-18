import edu.asu.engineeringed.users.*

class UserCreation {
    public static void loadData(){
        createRoles()
        createUsers()
    }
    
    private static void createRoles() {
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?:
                       new Role(authority:'ROLE_ADMIN').save()
        def switchUserRole = Role.findByAuthority('ROLE_SWITCH_USER') ?:
                       new Role(authority:'ROLE_SWITCH_USER').save()
        def userRole = Role.findByAuthority('ROLE_USER') ?:
                       new Role(authority:'ROLE_USER').save()
    }
    
    private static void createUsers(){
        def password = "password"
        def ganesh = User.findByUsername("ganesh") ?:
        new User(username:"ganesh", 
                 email:"tganesh@asu.edu",
                 enabled:true,
                 password:password).save()
        def nick = User.findByUsername("nick") ?:
        new User(username:"nick",
                 email:"the_N_Channel@asu.edu",
                 enabled:true,
                 password:password).save()
        def katie = User.findByUsername("katie") ?:
        new User(username:"katie",
                 email:"katherine.muto@asu.edu",
                 enabled:true,
                 password:password).save()
        def chrissy = User.findByUsername("chrissy") ?:
        new User(username:"chrissy",
                 email:"Christina.Foster@asu.edu",
                 enabled:true,
                 password:password).save()
        def wunmi = User.findByUsername("wunmi") ?:
        new User(username:"wunmi",
                 email:"Omowunmi.Isaacs-Sodeye@asu.edu",
                 enabled:true,
                 password:password).save()
        def carl = User.findByUsername("carl") ?:
        new User(username:"carl",
                 email:"Carl.Whitesel@asu.edu",
                 enabled:true,
                 password:password).save()
        def patrick = User.findByUsername("patrick") ?:
        new User(username:"patrick",
                 email:"Patrick.Schwab@asu.edu",
                 enabled:true,
                 password:password).save()
        UserRole.create(nick,Role.findByAuthority('ROLE_ADMIN'))
        UserRole.create(nick, Role.findByAuthority('ROLE_SWITCH_USER'))
        UserRole.create(ganesh, Role.findByAuthority('ROLE_USER'))
        UserRole.create(katie, Role.findByAuthority('ROLE_USER'))
        UserRole.create(chrissy, Role.findByAuthority('ROLE_USER'))
        UserRole.create(wunmi, Role.findByAuthority('ROLE_USER'))
        UserRole.create(carl, Role.findByAuthority('ROLE_USER'))
        UserRole.create(patrick, Role.findByAuthority('ROLE_USER'))
    }
}

