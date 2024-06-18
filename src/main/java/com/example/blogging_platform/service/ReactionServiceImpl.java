package com.example.blogging_platform.service;

import com.example.blogging_platform.dto.ReactionRequest;
import com.example.blogging_platform.dto.ReactionResponse;
import com.example.blogging_platform.exception.GeneralDatabaseException;
import com.example.blogging_platform.exception.PostNotFoundException;
import com.example.blogging_platform.mapper.ReactionMapper;
import com.example.blogging_platform.model.Post;
import com.example.blogging_platform.model.Reaction;
import com.example.blogging_platform.model.User;
import com.example.blogging_platform.repository.PostRepository;
import com.example.blogging_platform.repository.ReactionRepository;
import com.example.blogging_platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReactionMapper reactionMapper;

    @Transactional
    @Override
    public ReactionResponse addReaction(int postId, ReactionRequest reactionRequest) {
        //get auth user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //fetch user form db
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        // Find the post by ID
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        try {
            Reaction reaction = new Reaction();

            reaction.setReactionType(reactionRequest.reactionType());
            reaction.setUser(user);
            reaction.setPost(post);

            Reaction savedReaction = reactionRepository.save(reaction);

            return reactionMapper.INSTANCE.entityToReactionResponseDto(post.getId(), user.getId(), savedReaction);

        } catch (Exception e) {
            throw new GeneralDatabaseException("Error adding reaction", e);
        }
    }

    @Transactional
    @Override
    public void removeReaction(int postId) {
        try {
            //get auth user
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            //fetch user form db
            User user = userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found."));

            reactionRepository.deleteByPostIdAndUserId(postId, user.getId());

        } catch (Exception e) {
            throw new GeneralDatabaseException("Error removing reaction", e);
        }
    }
}
