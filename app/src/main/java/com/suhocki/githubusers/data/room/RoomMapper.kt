package com.suhocki.githubusers.data.room

import com.suhocki.githubusers.data.room.user.User
import com.suhocki.githubusers.data.room.userdetails.UserDetails
import com.suhocki.githubusers.domain.user.User as DomainUser
import com.suhocki.githubusers.domain.userdetails.UserDetails as DomainUserDetails

class RoomMapper {
    fun map(user: User) = DomainUser(
        id = user.id,
        login = user.login,
        url = user.url,
        avatarUrl = user.avatarUrl,
    )

    fun map(user: DomainUser) = User(
        id = user.id,
        login = user.login,
        url = user.url,
        avatarUrl = user.avatarUrl,
    )

    fun map(userDetails: UserDetails) = DomainUserDetails(
        login = userDetails.login,
        avatarUrl = userDetails.avatarUrl,
        name = userDetails.name,
        company = userDetails.company,
        blog = userDetails.blog,
        location = userDetails.location,
        email = userDetails.email,
        bio = userDetails.bio,
        twitterUsername = userDetails.twitterUsername,
        publicRepos = userDetails.publicRepos,
        publicGists = userDetails.publicGists,
        followers = userDetails.followers,
        following = userDetails.following,
    )

    fun map(userDetails: DomainUserDetails, url: String) = UserDetails(
        login = userDetails.login,
        avatarUrl = userDetails.avatarUrl,
        name = userDetails.name,
        company = userDetails.company,
        blog = userDetails.blog,
        location = userDetails.location,
        email = userDetails.email,
        bio = userDetails.bio,
        twitterUsername = userDetails.twitterUsername,
        publicRepos = userDetails.publicRepos,
        publicGists = userDetails.publicGists,
        followers = userDetails.followers,
        following = userDetails.following,
        detailsUrl = url,
    )
}