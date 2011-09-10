import edu.asu.engineeringed.users.*
import edu.asu.engineeringed.*
class BootStrap {
    def createInstitutions() {
        def asu = Institution.findByName("Arizona State University") ?:
            new Institution(name:"Arizona State University").save(failOnError:true)
    }
    def createRoles() {
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?:
                       new Role(authority:'ROLE_ADMIN').save(failOnError:true)
        def switchUserRole = Role.findByAuthority('ROLE_SWITCH_USER') ?:
                       new Role(authority:'ROLE_SWITCH_USER').save(failOnError:true)
        def userRole = Role.findByAuthority('ROLE_USER') ?:
                       new Role(authority:'ROLE_USER').save(failOnError:true)
    }
    
    def createUsers(){
        def password = "password"
        def ganesh = User.findByUsername("ganesh") ?:
        new User(username:"ganesh", 
                 email:"tganesh@asu.edu",
                 enabled:true,
                 password:password).save(failonError:true)
        def nick = User.findByUsername("nick") ?:
        new User(username:"nick",
                 email:"the_N_Channel@asu.edu",
                 enabled:true,
                 password:password).save(failonError:true)
        def katie = User.findByUsername("katie") ?:
        new User(username:"katie",
                 email:"katherine.muto@asu.edu",
                 enabled:true,
                 password:password).save(failonError:true)
        def chrissy = User.findByUsername("chrissy") ?:
        new User(username:"chrissy",
                 email:"Christina.Foster@asu.edu",
                 enabled:true,
                 password:password).save(failonError:true)
        def wunmi = User.findByUsername("wunmi") ?:
        new User(username:"wunmi",
                 email:"Omowunmi.Isaacs-Sodeye@asu.edu",
                 enabled:true,
                 password:password).save(failonError:true)
        def carl = User.findByUsername("carl") ?:
        new User(username:"carl",
                 email:"Carl.Whitesel@asu.edu",
                 enabled:true,
                 password:password).save(failonError:true)
        def patrick = User.findByUsername("patrick") ?:
        new User(username:"patrick",
                 email:"Patrick.Schwab@asu.edu",
                 enabled:true,
                 password:password).save(failonError:true)
        UserRole.create(nick,Role.findByAuthority('ROLE_ADMIN'))
        UserRole.create(nick, Role.findByAuthority('ROLE_SWITCH_USER'))
        UserRole.create(ganesh, Role.findByAuthority('ROLE_USER'))
        UserRole.create(katie, Role.findByAuthority('ROLE_USER'))
        UserRole.create(chrissy, Role.findByAuthority('ROLE_USER'))
        UserRole.create(wunmi, Role.findByAuthority('ROLE_USER'))
        UserRole.create(carl, Role.findByAuthority('ROLE_USER'))
        UserRole.create(patrick, Role.findByAuthority('ROLE_USER'))
    }
    def init = { servletContext ->
        createInstitutions()
        createRoles()
        createUsers()
    }
    def destroy = {
    }
}
