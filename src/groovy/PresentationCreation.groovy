import edu.asu.engineeringed.*
import edu.asu.engineeringed.presentations.*
import java.text.SimpleDateFormat

class PresentationCreation implements CreationAlgorithm {

    public static void loadData(){
        createAnn()
        createDale()
        createBrad()
    }
    
    private static void createAnn(){
        def speaker = Speaker.findByName("Ann F. McKenna") ?:
                     new Speaker(
                         name:"Ann F. McKenna",
                         email:"Ann.McKenna@asu.edu",
                         bio:"""Ann McKenna is an Associate Professor in the Department of Engineering in the College of Technology and Innovation at Arizona State University (ASU). Prior to joining ASU this Fall 2010 semester, she served as a program officer at the National Science Foundation in the Division of Undergraduate Education. Dr. McKenna's research focuses on understanding the cognitive and social processes of design, design teaching and learning, the role of adaptive expertise and the use of representations in design and innovation, the diffusion of educational innovations, and teaching approaches of engineering faculty. Dr. McKenna received her B.S. and M.S. degrees in Mechanical Engineering from Drexel University and Ph.D. from the University of California at Berkeley. """
                     ).save(failOnError:true)
        def title = "Knowledge Fluency in Engineering Design and Innovation"
        def description = """This presentation provides an overview of a study investigating how students apply disciplinary knowledge in the process of design. Using the adaptive expertise framework, with a specific focus on computational and analytical knowledge, the project documents the type of evidence students' use in the process of innovation, and how students approach using modeling in the process of design."""
        def talk = Presentation.findByTitle(title) ?:
            new Presentation(title:title,
                             description:description,
                             speaker:speaker,
                             location:"University Club, Tempe Campus",
                             start: new SimpleDateFormat("MM/d/yyyy h:mm a").parse("10/22/2010 12:30 pm"),
                             end: new SimpleDateFormat("MM/dd/yyyy h:mm a").parse("10/22/2010 1:30 pm")
                            ).save(failOnError:true)
        def file = "ASU_Eg_Ed_Seminar_10.22.10.pdf"
        def mediaData = new File("web-app/files/${file}").bytes 
        def media = Media.findByName(file) ?:
                     new Media(name:file,
                               description:description,
                               fileData:mediaData
                              ).save(failOnError:true)
        talk.addToAttachments(media)
    }
    
    private static void createDale(){
        def speaker = Speaker.findByName("Dale R. Baker") ?:
                     new Speaker(
                         name:"Dale R. Baker",
                         email:"DALE.BAKER@asu.edu",
                         bio:"""Dale Baker received her EdD in science education from Rutgers University in 1982. Her areas of research have focused on gender issues in science and engineering and teacher professional development. She is a fellow of both the American Educational Research Association and the American Association for the Advancement of Science. She was co-editor of the Journal of Research in Science Teaching, 2000-2005, and is currently on the Advisory Board for the Journal of Engineering Education. She has worked on gender equity projects in Japan and, under the auspices of the World Bank, developed the university curriculum for the preparation of elementary science teachers in Turkey as well as trained university faculty to use the curriculum. In 2003, her article "Letting girls Speak" was chosen as one of the 13 most influential research in science education in the past forty years published in the Journal of Research in Science Teaching and reprinted in the journal's 40th anniversary issue (i.e., Baker, D. & Leary, R., (1995). Letting girls speak out about science. Journal of Research in Science Teaching, 1, 3-27.)"""
                     ).save(failOnError:true)
        def title = "Instructional Interventions in Fostering Women's Interest, Retention, and Success in Undergraduate Engineering"
        def description = """Engineering has been less successful than chemistry, biology; psychology; agricultural science; and earth, atmospheric and ocean science in increasing the number of women earning bachelor's degrees. According to the National Science Board's 2010 report, women's share of bachelor's degrees in computer sciences, mathematics, and engineering has declined in recent years. This talk will explore the reasons for the low numbers of women in engineering and describe the instructional interventions in related research, that if applied in engineering, have the potential to increase women's interest, retention, and success in engineering."""
        def talk = Presentation.findByTitle(title) ?:
            new Presentation(title:title,
                             description:description,
                             speaker:speaker,
                             location:"University Club, Tempe Campus",
                             start: new SimpleDateFormat("MM/d/yyyy h:mm a").parse("11/12/2010 12:30 pm"),
                             end: new SimpleDateFormat("MM/dd/yyyy h:mm a").parse("11/12/2010 1:30 pm")
                            ).save(failOnError:true)
        def file = "ASU_Eg_Ed_Seminar_11.12.10.pdf"
        def mediaData = new File("web-app/files/${file}").bytes 
        def media = Media.findByName(file) ?:
                     new Media(name:file,
                               description:description,
                               fileData:mediaData
                              ).save(failOnError:true)
        talk.addToAttachments(media)
    }
    
    private static void createBrad(){
        def speaker = Speaker.findByName("Braden R. Allenby") ?:
                     new Speaker(
                         name:"Braden R. Allenby",
                         email:"Braden.Allenby@asu.edu",
                         bio:"Braden R. Allenby is Lincoln Professor of Engineering and Ethics; professor of Civil, Environmental, and Sustainable Engineering, and of Law; Founding Chair of the Consortium for Emerging Technologies, Military Operations, and National Security; and Founding Director of the Center for Earth Systems Engineering and Management, at Arizona State University. He moved to ASU from his previous position as the Environment, Health and Safety Vice President for AT&T in 2004. Dr. Allenby received his BA from Yale University, his JD and MA (economics) from the University of Virginia, and his MS and Ph.D. in Environmental Sciences from Rutgers University. He is past President of the International Society for Industrial Ecology; ex-Chair of the AAAS Committee on Science, Engineering, and Public Policy; Chair of the IEEE Presidential Sustainability Initiative; a U. S. Naval Academy Stockdale Fellow; an AAAS Fellow; an AT&T Industrial Ecology Fellow; a Templeton Research Fellow; a Batten Fellow in Residence at the University of Virginia's Darden Graduate School of Business Administration; and a Fellow of the Royal Society for the Arts, Manufactures & Commerce. From 1995 to 1997, he was Director for Energy and Environmental Systems at Lawrence Livermore National Laboratory, and from 1991 to 1992 he was the J. Herbert Holloman Fellow at the National Academy of Engineering in Washington, DC. His areas of expertise include Design for Environment, industrial ecology, telework and netcentric organizations, transhumanism, and earth systems engineering and management. In 2008 he was selected by the Carnegie Foundation as 2008 Arizona Professor of the Year. His latest book, co-authored with Tom Graedel, is Industrial Ecology and Sustainable Engineering, released in fall 2009 by Prentice-Hall; his next two books are The Techno-Human Condition (in press at the MIT Press), and The Theory and Practice of Sustainable Engineering (in press at Pearson/Prentice Hall)."
                     ).save(failOnError:true)
        def title = "Rethinking Engineering Education"
        def description = """The necessity of restructuring engineering education has been recognized for many years, but for a number of reasons reform is becoming increasingly urgent. It is not just that current engineering education methods are increasingly obsolete; student cognitive patterns are changing in unpredictable ways, and the complexity of the environment within which engineering is practiced is also increasing dramatically. Half measures that might have been seen as adequate a decade ago are no longer sufficient. What is required is a thorough rethinking of the engineering education framework, centered around a division of engineering students into technical, single expertise, and design capable cohorts."""
        def talk = Presentation.findByTitle(title) ?:
            new Presentation(title:title,
                             description:description,
                             speaker:speaker,
                             location:"University Club, Tempe Campus",
                             start: new SimpleDateFormat("MM/d/yyyy h:mm a").parse("12/03/2010 11:00 am"),
                             end: new SimpleDateFormat("MM/dd/yyyy h:mm a").parse("12/03/2010 12:00 pm")
                            ).save(failOnError:true)
        def file = "ASU_Eg_Ed_Seminar_12.03.10.pdf"
        def mediaData = new File("web-app/files/${file}").bytes 
        def media = Media.findByName(file) ?:
                     new Media(name:file,
                               description:description,
                               fileData:mediaData
                              ).save(failOnError:true)
        talk.addToAttachments(media)
        
        file = "Brad_Allenby_IEEE_Paper_2010.pdf"
        description = "Dr. Allenby's paper to IEEE"
        mediaData = new File("web-app/files/${file}").bytes
        media = Media.findByName(file) ?:
                     new Media(name:file,
                               description:description,
                               fileData:mediaData
                              ).save(failOnError:true)
        talk.addToAttachments(media)
    }
}

