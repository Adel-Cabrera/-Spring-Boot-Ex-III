package com.darkonnen.examenfinal.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.darkonnen.examenfinal.entities.Rating;
import com.darkonnen.examenfinal.entities.Show;
import com.darkonnen.examenfinal.entities.User;
import com.darkonnen.examenfinal.services.RatingService;
import com.darkonnen.examenfinal.services.ShowService;
import com.darkonnen.examenfinal.services.UserService;

@Controller
@RequestMapping("/shows")
public class ShowController {

	@Autowired
	private UserService userService;

	@Autowired
	private ShowService showService;

	@Autowired
	private RatingService ratingService;

	// ADD RATING

	@PostMapping(value = "/{id}/add")
	public String addRating(Principal principal, @Valid @ModelAttribute("addRating") Rating rating,
			BindingResult result, @PathVariable("id") Long id,  Model model) {
		if (result.hasErrors()) {
			return "redirect:/shows/" + id;
		} else {
			String email = principal.getName();
			User currentUser = userService.findUserByEmail(email);
			Show currentShow = showService.findShowById(id);
//			List<Rating> showRatings= currentShow.getRatings();
			
//			int currentRatingID = rating.getRating();
//			Rating currentRating = rating;
//			currentRating.setId((long) (currentRatingID + 1)); // ESTAS LÍNEAS FUNCIONAN
//			ratingService.addRating(currentRating);
//			return "redirect:/shows/" + id;

			
//			rating.setUser(currentUser);
//			rating.setShow(currentShow); // TAMBIÉN FUNCIONAN
//			rating.setId(rating.getId());
			
			
			ratingService.addRating(rating, currentShow, currentUser);
//			currentShow.setRatings(showRatings);
			showService.saveShow(currentShow);
			
			return "redirect:/shows/" + id;
		}
	}

	// READ ONE

	@GetMapping(value = "/{id}")
	public String getShow(@PathVariable("id") Long id, Model model,	Principal principal) { //, @ModelAttribute("addRating") Rating rating,
		Show show = showService.findShowById(id);
		User creatorShow = show.getCreatorShow();
		String email = principal.getName();
		User currentUser = userService.findUserByEmail(email);
		List<Rating> showRatings = show.getRatings();
//		List<Rating> allRatings = ratingService.findAllRatings();
		Rating newRating = new Rating();
		model.addAttribute("addRating", newRating);
		model.addAttribute("showRatings", showRatings);
//		model.addAttribute("allRatings", allRatings);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("show", show);
		model.addAttribute("creatorShow", creatorShow);

		return "show";
	}

	// READ ALL

	@GetMapping("")
	public String homePage(Principal principal, Model model) {
		String email = principal.getName();
		User currentUser = userService.findUserByEmail(email);
//		List<Role> userRoles = currentUser.getRoles();
		List<Show> allShows = showService.findAllShows();
		// TODO SHOW AVERAGE

		model.addAttribute("allShows", allShows);
		model.addAttribute("currentUser", currentUser);
//		model.addAttribute("userRoles", userRoles);

		return "home";
	}

	// CREATE NEW
	@GetMapping(value = "/new")
	public String newShow(Model model) {
		Show newShow = new Show();
		model.addAttribute("newShow", newShow);
		return "new";
	}

	// CREATE SAVE

	@PostMapping(value = "/create")
	public String createShow(@ModelAttribute("newShow") @Valid Show show, BindingResult result, Principal principal) {

		if (result.hasErrors()) {
			return "new"; // -> O CAMBIAR POR REDIRECCIÓN
		} else {
			String email = principal.getName();
			User creatorShow = userService.findUserByEmail(email);
			show.setCreatorShow(creatorShow);
			;
			showService.saveShow(show);
			return "redirect:/shows";
		}

	}

	// EDIT
	@GetMapping(value = "/{id}/edit")
	public String editShow(@PathVariable("id") Long id, Model model, @ModelAttribute("errors") String errors) {
		Show editShow = showService.findShowById(id);
		model.addAttribute("editShow", editShow);

		return "edit";
	}

	// UPDATE
	@PostMapping(value = "/{id}/edit")
	public String updateShow(@PathVariable("id") Long id, @Valid @ModelAttribute("editShow") Show editedShow,
			BindingResult result, Model model, Principal principal, HttpSession session) {
		String email = principal.getName();
		Show show = showService.findShowById(id);
		User creatorShow = userService.findUserByEmail(email);

		if (result.hasErrors()) {
//			return "redirect:/shows/" + show.getId() + "/edit";
			session.setAttribute("id", show.getId());
			return "redirect:/shows/createError";
		} else {
			editedShow.setCreatorShow(creatorShow);
			showService.updateShow(editedShow);
			return "redirect:/shows/";
		}

	}

	@RequestMapping("/createError")
	public String flashMessages(RedirectAttributes redirectAttributes, HttpSession session) {
		redirectAttributes.addFlashAttribute("errors", "Title and Network must be present");
		return "redirect:/shows/" + session.getAttribute("id") + "/edit";
	}

	// DELETE / DESTROY

	@GetMapping(value = "/{id}/delete")
	public String deleteShow(@PathVariable("id") Long id) {
		showService.deleteShow(id);
		return "redirect:/shows/";
	}

}
