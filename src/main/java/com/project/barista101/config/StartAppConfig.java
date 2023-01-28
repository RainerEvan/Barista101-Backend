package com.project.barista101.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.barista101.data.ERoles;
import com.project.barista101.model.account.Accounts;
import com.project.barista101.model.account.Roles;
import com.project.barista101.model.brew.Brews;
import com.project.barista101.model.course.Contents;
import com.project.barista101.model.course.Courses;
import com.project.barista101.model.course.Enrollments;
import com.project.barista101.model.course.Modules;
import com.project.barista101.model.forum.ForumComments;
import com.project.barista101.model.forum.Forums;
import com.project.barista101.model.notification.Notifications;
import com.project.barista101.model.recipe.RecipeCategories;
import com.project.barista101.model.recipe.RecipeRatings;
import com.project.barista101.model.recipe.Recipes;
import com.project.barista101.payload.request.BrewRequest;
import com.project.barista101.payload.request.ContentRequest;
import com.project.barista101.payload.request.CourseRequest;
import com.project.barista101.payload.request.EnrollmentRequest;
import com.project.barista101.payload.request.ForumCommentRequest;
import com.project.barista101.payload.request.ForumRequest;
import com.project.barista101.payload.request.ModuleRequest;
import com.project.barista101.payload.request.NotificationRequest;
import com.project.barista101.payload.request.RecipeRatingRequest;
import com.project.barista101.payload.request.RecipeRequest;
import com.project.barista101.payload.request.SignupRequest;
import com.project.barista101.repository.RoleRepository;
import com.project.barista101.service.AccountService;
import com.project.barista101.service.BrewService;
import com.project.barista101.service.ContentService;
import com.project.barista101.service.CourseService;
import com.project.barista101.service.EnrollmentService;
import com.project.barista101.service.ForumCommentService;
import com.project.barista101.service.ForumService;
import com.project.barista101.service.ModuleService;
import com.project.barista101.service.NotificationService;
import com.project.barista101.service.RecipeCategoryService;
import com.project.barista101.service.RecipeRatingService;
import com.project.barista101.service.RecipeService;

@Configuration
public class StartAppConfig {
    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository, AccountService accountService, CourseService courseService, ModuleService moduleService, ContentService contentService, EnrollmentService enrollmentService, ForumService forumService, ForumCommentService forumCommentService, RecipeService recipeService, RecipeCategoryService recipeCategoryService, RecipeRatingService recipeRatingService, NotificationService notificationService, BrewService brewService){
        return args -> {

            Roles role1 = new Roles();
            role1.setName(ERoles.ADMIN);
            roleRepository.save(role1);

            Roles role2 = new Roles();
            role2.setName(ERoles.USER);
            roleRepository.save(role2);
            
            // SignupRequest signupRequest1 = new SignupRequest("rainerevan@gmail.com", "Rainer Evan", "rainerevan", "pass123",ERoles.USER);
            // Accounts account = accountService.addAccount(signupRequest1);

            // SignupRequest signupRequest2 = new SignupRequest("maman@gmail.com", "Maman Johnson", "maman", "pass123",ERoles.USER);
            // Accounts account2 = accountService.addAccount(signupRequest2);

            SignupRequest signupRequest3 = new SignupRequest("admin@gmail.com", "Admin", "admin", "pass123",ERoles.ADMIN);
            Accounts account3 = accountService.addAccount(signupRequest3);

            // CourseRequest courseRequest1 = new CourseRequest("Beans", "Learn about the coffee beans and how they are being an important factor for your cup of coffee");
            // Courses course1 = courseService.addCourse(null, courseRequest1);

            // ModuleRequest moduleRequest1 = new ModuleRequest(course1.getId(), "Beans Origin");
            // Modules module1 = moduleService.addModule(null, moduleRequest1);

            // ContentRequest contentRequest1 = new ContentRequest(module1.getId(), "Where do coffee beans come from?", "Coffee beans come from the coffee plant, a bush-like plant which can get very tall (coffee farmers will usually keep them trimmed to around 5ft to keep them manageable). On these coffee plants, bunches of cherries grow and inside these you’ll find two coffee beans, Arabica and Robusta coffee");
            // Contents content1 = contentService.addContent(null, contentRequest1);

            // ContentRequest contentRequest2 = new ContentRequest(module1.getId(), "When is the coffee plant is ready?","On average, it takes around one year for the coffee plant to begin to produce fragrant, white flowers, then up to four years later before it begins to bear fruit. It will be around 10 years for the coffee plants to begin producing coffee beans on a commercial level, which are the ones of the most value to the farmers. The general lifespan of the coffee plant will be between 30 to 40 years but they can live much longer depending on the care given!<br>Once they're ripe and ready for picking they'll turn red in colour, but it takes a keen eye to know when the berries are ready for harvest as picking too early or too late can have a huge impact on the final taste.");
            // Contents content2 = contentService.addContent(null, contentRequest2);

            // ContentRequest contentRequest3 = new ContentRequest(module1.getId(), "Where is coffee grown?", "Most coffee plants are grown around what’s known as ‘the bean belt’, an area around the equator between the tropics of Capricorn and Cancer. It’s here that’s home to the coffee capitals of the world such as Brazil, Vietnam, Colombia, Indonesia and Ethiopia, as these are the locations with the perfect conditions for coffee plants to thrive.<br>Interestingly, the location of where coffee beans are grown can alter the taste. Things such as climate, elevation and even soil type can impact the flavour of the coffee the beans produce.");
            // Contents content3 = contentService.addContent(null, contentRequest3);

            // ModuleRequest moduleRequest2 = new ModuleRequest(course1.getId(), "Post Processing");
            // Modules module2 = moduleService.addModule(null, moduleRequest2);

            // ContentRequest contentRequest4 = new ContentRequest(module2.getId(), "Where do coffee beans come from?", "Coffee beans come from the coffee plant, a bush-like plant which can get very tall (coffee farmers will usually keep them trimmed to around 5ft to keep them manageable). On these coffee plants, bunches of cherries grow and inside these you’ll find two coffee beans, Arabica and Robusta coffee");
            // Contents content4 = contentService.addContent(null, contentRequest4);

            // ContentRequest contentRequest5 = new ContentRequest(module2.getId(), "When is the coffee plant is ready?","On average, it takes around one year for the coffee plant to begin to produce fragrant, white flowers, then up to four years later before it begins to bear fruit. It will be around 10 years for the coffee plants to begin producing coffee beans on a commercial level, which are the ones of the most value to the farmers. The general lifespan of the coffee plant will be between 30 to 40 years but they can live much longer depending on the care given!<br>Once they're ripe and ready for picking they'll turn red in colour, but it takes a keen eye to know when the berries are ready for harvest as picking too early or too late can have a huge impact on the final taste.");
            // Contents content5 = contentService.addContent(null, contentRequest5);
            
            // ModuleRequest moduleRequest3 = new ModuleRequest(course1.getId(), "Roasting");
            // Modules module3 = moduleService.addModule(null, moduleRequest3);

            // ContentRequest contentRequest6 = new ContentRequest(module3.getId(), "Where do coffee beans come from?", "Coffee beans come from the coffee plant, a bush-like plant which can get very tall (coffee farmers will usually keep them trimmed to around 5ft to keep them manageable). On these coffee plants, bunches of cherries grow and inside these you’ll find two coffee beans, Arabica and Robusta coffee");
            // Contents content6 = contentService.addContent(null, contentRequest6);

            // ContentRequest contentRequest7 = new ContentRequest(module3.getId(), "When is the coffee plant is ready?","On average, it takes around one year for the coffee plant to begin to produce fragrant, white flowers, then up to four years later before it begins to bear fruit. It will be around 10 years for the coffee plants to begin producing coffee beans on a commercial level, which are the ones of the most value to the farmers. The general lifespan of the coffee plant will be between 30 to 40 years but they can live much longer depending on the care given!<br>Once they're ripe and ready for picking they'll turn red in colour, but it takes a keen eye to know when the berries are ready for harvest as picking too early or too late can have a huge impact on the final taste.");
            // Contents content7 = contentService.addContent(null, contentRequest7);

            // ForumRequest forumRequest1 = new ForumRequest(account.getId(),"The Magic of Cold Brew","Cold brew is simply coffee that has been brewed with cold rather than hot water and usually involves a long steeping process—anywhere between 12-24 hours. In terms of flavor, cold brew is generally characterized as smooth, low-acid, and heavier than its hot brewed counterparts.<br><br>Although cold brew has been around for centuries, it’s really in the last ten years that it’s become a staple on coffee shops menus and recognizable by most coffee drinkers");
            // Forums forum1 = forumService.addForum(null,forumRequest1);
           
            // ForumRequest forumRequest2 = new ForumRequest(account.getId(),"Most Amazing Coffee","Most coffee plants are grown around what’s known as ‘the bean belt’, an area around the equator between the tropics of Capricorn and Cancer. It’s here that’s home to the coffee capitals of the world such as Brazil, Vietnam, Colombia, Indonesia and Ethiopia, as these are the locations with the perfect conditions for coffee plants to thrive.<br>Interestingly, the location of where coffee beans are grown can alter the taste. Things such as climate, elevation and even soil type can impact the flavour of the coffee the beans produce.");
            // Forums forum2 = forumService.addForum(null,forumRequest2);

            // ForumCommentRequest forumCommentRequest1 = new ForumCommentRequest(forum2.getId(), account.getId(), "Agreed, what a great information about coffee in Indonesia");
            // ForumComments forumComment1 = forumCommentService.addForumComment(forumCommentRequest1);

            // ForumCommentRequest forumCommentRequest2 = new ForumCommentRequest(forum2.getId(), account.getId(), "Really inspiring stuff");
            // ForumComments forumComment2 = forumCommentService.addForumComment(forumCommentRequest2);

            // RecipeCategories category1 = recipeCategoryService.addRecipeCategory("Cocktail");
            // RecipeCategories category2 = recipeCategoryService.addRecipeCategory("Frappucino");
            // RecipeCategories category3 = recipeCategoryService.addRecipeCategory("Manual Brew");

            // RecipeRequest recipeRequest1 = new RecipeRequest(category1.getId(), account.getId(), "Martini", "The whiskey sour is a mixed drink containing whiskey, lemon juice, sugar, and optionally, a dash of egg white or few droplets of vegan foamer. It is a type of sour, a mixed drink with a base spirit, citrus juice, and a sweetener.", "[{\"item\":\"Glass\"},{\"item\":\"Shaker\"},{\"item\":\"Spoon\"}]", "[{\"item\":\"Whiskey\"},{\"item\":\"Lemon Juice\"},{\"item\":\"Soda Water\"}]", "[{\"item\":\"Pour 30 ml of whiskey into the shaker\"},{\"item\":\"Squeeze 20 ml of lemon and add the juice into the shaker\"},{\"item\":\"Add 5 pcs of ice cubes into the shaker\"}]", "Try adding more ice cube to get the chills.");
            // Recipes recipe1 = recipeService.addRecipe(null, recipeRequest1);
            
            // RecipeRequest recipeRequest2 = new RecipeRequest(category1.getId(), account.getId(), "Negroni", "The whiskey sour is a mixed drink containing whiskey, lemon juice, sugar, and optionally, a dash of egg white or few droplets of vegan foamer. It is a type of sour, a mixed drink with a base spirit, citrus juice, and a sweetener.", "[{\"item\":\"Glass\"},{\"item\":\"Shaker\"},{\"item\":\"Spoon\"}]", "[{\"item\":\"Whiskey\"},{\"item\":\"Lemon Juice\"},{\"item\":\"Soda Water\"}]", "[{\"item\":\"Pour 30 ml of whiskey into the shaker\"},{\"item\":\"Squeeze 20 ml of lemon and add the juice into the shaker\"},{\"item\":\"Add 5 pcs of ice cubes into the shaker\"}]", "Try adding more ice cube to get the chills.");
            // Recipes recipe2 = recipeService.addRecipe(null, recipeRequest2);
            
            // RecipeRequest recipeRequest3 = new RecipeRequest(category1.getId(), account.getId(), "Whiskey Sour", "The whiskey sour is a mixed drink containing whiskey, lemon juice, sugar, and optionally, a dash of egg white or few droplets of vegan foamer. It is a type of sour, a mixed drink with a base spirit, citrus juice, and a sweetener.", "[{\"item\":\"Glass\"},{\"item\":\"Shaker\"},{\"item\":\"Spoon\"}]", "[{\"item\":\"Whiskey\"},{\"item\":\"Lemon Juice\"},{\"item\":\"Soda Water\"}]", "[{\"item\":\"Pour 30 ml of whiskey into the shaker\"},{\"item\":\"Squeeze 20 ml of lemon and add the juice into the shaker\"},{\"item\":\"Add 5 pcs of ice cubes into the shaker\"}]", "Try adding more ice cube to get the chills.");
            // Recipes recipe3 = recipeService.addRecipe(null, recipeRequest3);

            // RecipeRatingRequest recipeRatingRequest1 = new RecipeRatingRequest(recipe3.getId(), account2.getId(), 5, "This is a great recipe");
            // RecipeRatings recipeRating1 = recipeRatingService.addRecipeRating(recipeRatingRequest1);

            // BrewRequest brewRequest1 = new BrewRequest("V60 Drip", "Brew with V60 to discover the light, sweet and clear nature of coffees", 15, 225, 15, 130, "medium", "[{\"item\":\"Ground Coffee\",\"status\":false},{\"item\":\"V60 Dripper\",\"status\":false},{\"item\":\"V60 Paper\",\"status\":false},{\"item\":\"Kettle\",\"status\":false},{\"item\":\"Coffee Server\",\"status\":false}]", "[{\"desc\":\"Pour {water} gr of water to start the blooming process\",\"water\":0.2,\"seconds\":15,\"pour\":true},{\"desc\":\"Swirl gently and wait for the coffee to bloom\",\"water\":0,\"seconds\":30,\"pour\":false},{\"desc\":\"Pour another {water} gr of water\",\"water\":0.4,\"seconds\":60,\"pour\":true},{\"desc\":\"Wait for the water to drain a bit\",\"water\":0,\"seconds\":80,\"pour\":false},{\"desc\":\"Pour the remaining {water} gr of water\",\"water\":0.4,\"seconds\":110,\"pour\":true},{\"desc\":\"Let all the water to drain through and serve\",\"water\":0,\"seconds\":130,\"pour\":false}]");
            // Brews brew1 = brewService.addBrew(null, brewRequest1);
        };
    }
}
