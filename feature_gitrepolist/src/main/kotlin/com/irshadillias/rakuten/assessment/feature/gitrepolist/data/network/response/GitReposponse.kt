package com.irshadillias.rakuten.assessment.feature.gitrepolist.data.network.response

import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.model.RepositoryData
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.model.RepositoryList
import com.squareup.moshi.Json

data class Owner(
    @field:Json(name = "account_id")
    val accountId: String? = "",

    @field:Json(name = "nickname")
    val nickname: String? = "",
    @field:Json(name = "links")
    val links: Links?,
    @field:Json(name = "display_name")
    val displayName: String? = "",
    @field:Json(name = "type")
    val type: String? = "",
    @field:Json(name = "uuid")
    val uuid: String? = ""
)

data class Commits(
    @field:Json(name = "href")
    val href: String = ""
)

data class Mainbranch(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "type")
    val type: String = ""
)

data class Branches(
    @field:Json(name = "href")
    val href: String = ""
)

internal data class GitReposponse(
    @field:Json(name = "next")
    val next: String = "",
    @field:Json(name = "values")
    val values: List<ValuesItem>,
    @field:Json(name = "pagelen")
    val pagelen: Int = 0
)

internal fun GitReposponse.gitRepoToViewData(): RepositoryData {

    return RepositoryData(
        next = next,
        repoList = values.map { it.toViewData() }
    )
}

internal  fun ValuesItem.toViewData(): RepositoryList {

    return RepositoryList(
        imageUrl = owner.links?.avatar?.href.orEmpty(),
        name = owner.displayName.orEmpty(),
        type = project.type,
        timestamp = createdOn,
        size = (size / 1000).toString() + " MB",
        description = description,
        uuid = uuid,
        website = website

    )
}

data class Html(
    @field:Json(name = "href")
    val href: String = ""
)

data class Self(
    @field:Json(name = "href")
    val href: String = ""
)

data class Avatar(
    @field:Json(name = "href")
    val href: String = ""
)

data class Source(
    @field:Json(name = "href")
    val href: String = ""
)

data class Watchers(
    @field:Json(name = "href")
    val href: String = ""
)

data class Project(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "links")
    val links: Links,
    @field:Json(name = "type")
    val type: String = "",
    @field:Json(name = "uuid")
    val uuid: String = "",
    @field:Json(name = "key")
    val key: String = ""
)

data class CloneItem(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "href")
    val href: String = ""
)

data class ValuesItem(
    @field:Json(name = "owner")
    val owner: Owner,
    @field:Json(name = "updated_on")
    val updatedOn: String = "",
    @field:Json(name = "is_private")
    val isPrivate: Boolean = false,
    @field:Json(name = "website")
    val website: String = "",
    @field:Json(name = "workspace")
    val workspace: Workspace,
    @field:Json(name = "fork_policy")
    val forkPolicy: String = "",
    @field:Json(name = "project")
    val project: Project,
    @field:Json(name = "description")
    val description: String = "",
    @field:Json(name = "language")
    val language: String = "",
    @field:Json(name = "type")
    val type: String = "",
    @field:Json(name = "uuid")
    val uuid: String = "",
    @field:Json(name = "has_issues")
    val hasIssues: Boolean = false,
    @field:Json(name = "mainbranch")
    val mainbranch: Mainbranch,
    @field:Json(name = "has_wiki")
    val hasWiki: Boolean = false,
    @field:Json(name = "full_name")
    val fullName: String = "",
    @field:Json(name = "size")
    val size: Int = 0,
    @field:Json(name = "created_on")
    val createdOn: String = "",
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "links")
    val links: Links,
    @field:Json(name = "scm")
    val scm: String = "",
    @field:Json(name = "slug")
    val slug: String = ""
)

data class Links(
    @field:Json(name = "forks")
    val forks: Forks,
    @field:Json(name = "watchers")
    val watchers: Watchers,
    @field:Json(name = "source")
    val source: Source,
    @field:Json(name = "avatar")
    val avatar: Avatar,
    @field:Json(name = "branches")
    val branches: Branches,
    @field:Json(name = "pullrequests")
    val pullrequests: Pullrequests,
    @field:Json(name = "tags")
    val tags: Tags,
    @field:Json(name = "downloads")
    val downloads: Downloads,
    @field:Json(name = "clone")
    val clone: List<CloneItem>?,
    @field:Json(name = "commits")
    val commits: Commits,
    @field:Json(name = "self")
    val self: Self,
    @field:Json(name = "html")
    val html: Html,
    @field:Json(name = "hooks")
    val hooks: Hooks
)

data class Pullrequests(
    @field:Json(name = "href")
    val href: String = ""
)

data class Hooks(
    @field:Json(name = "href")
    val href: String = ""
)

data class Downloads(
    @field:Json(name = "href")
    val href: String = ""
)

data class Tags(
    @field:Json(name = "href")
    val href: String = ""
)

data class Workspace(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "links")
    val links: Links,
    @field:Json(name = "type")
    val type: String = "",
    @field:Json(name = "uuid")
    val uuid: String = "",
    @field:Json(name = "slug")
    val slug: String = ""
)

data class Forks(
    @field:Json(name = "href")
    val href: String = ""
)
